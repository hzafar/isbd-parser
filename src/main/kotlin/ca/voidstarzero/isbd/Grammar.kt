package ca.voidstarzero.isbd

import ca.voidstarzero.isbd.ast.*
import norswap.autumn.Autumn
import norswap.autumn.DSL
import norswap.autumn.ParseOptions
import norswap.autumn.ParseState
import norswap.autumn.parsers.AbstractForwarding
import norswap.autumn.parsers.CharPredicate

class Grammar : DSL() {

    val store: ParseState<ParseContext> = ParseState(Grammar::class, ::ParseContext)

    private val colon = rule(
        object : AbstractForwarding(
            "colon",
            str("_:_")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.currentState
                        context.currentState = ParseContext.State.OTHER_INFO
                        return@apply Runnable {
                            context.currentState = oldState
                        }
                    }
                }.get()
        ) {}
    )
    private val slash = rule(
        object : AbstractForwarding(
            "slash",
            str("_/_")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.currentState
                        context.currentState = ParseContext.State.SOR
                        return@apply Runnable {
                            context.currentState = oldState
                        }
                    }
                }.get()
        ) {}
    )

    private val equalSign: rule = str("_=_")
    private val semicolon: rule = str("_;_")
    private val period: rule = str(". ")

    private val char = rule(CharPredicate("char") {
        it !in listOf('\u0000', '_', ' ', '.', '\t', '\n', '\r').map(Char::toInt)
    })

    private val data: rule = char.at_least(1).sep(0, usual_whitespace)
        .push(with_string { _, _, match -> match })

    private val otherInfo: rule = seq(colon, data)
        .push { items -> OtherInfo(items[0] as String) }

    private val otherInfoList: rule = otherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as OtherInfo })
        }

    private val sor: rule = seq(slash, data)
        .push { items -> SOR(items[0] as String) }

    private val additionalSOR: rule = seq(semicolon, data).at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { SOR(it as String) })
        }

    private val sorList: rule = seq(sor, additionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as SOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }

    private val title: rule = data
        .push { items -> Title(items[0] as String) }

    private val parallelData = rule(
        object : AbstractForwarding(
            "parallel_data",
            seq(equalSign, data)
                .collect().action_with_string { parse, items, _ ->
                    val context = store.data(parse)
                    val toPush: Any? = when (context.currentState) {
                        ParseContext.State.OTHER_INFO -> ParallelOtherInfo(items[0] as String)
                        ParseContext.State.SOR -> ParallelSOR(items[0] as String)
                        else -> null
                    }
                    parse.stack.push(toPush)
                }.get()
        ) {}
    )

    private val parallelOtherInfo: rule = seq(colon, data)
        .push { items -> ParallelOtherInfo(items[0] as String) }

    private val parallelOtherInfoList: rule = parallelOtherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as ParallelOtherInfo })
        }

    private val parallelSOR: rule = seq(slash, data)
        .push { items ->
            ParallelSOR(items[0] as String)
        }

    private val parallelAdditionalSOR: rule = seq(semicolon, data).at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { ParallelSOR(it as String) })
        }

    private val parallelSORList: rule = seq(parallelSOR, parallelAdditionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as ParallelSOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }

    private val parallelTitle: rule = seq(equalSign, data)
        .push { items -> ParallelTitle(items[0] as String) }

    private val parallelTitleList: rule = parallelTitle.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as ParallelTitle })
        }

    private val parallelTitleAndOtherInfo: rule = seq(parallelTitle, parallelOtherInfoList)
        .push { items ->
            NodeList(
                listOf(items[0] as ParallelTitle)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }

    private val parallelTitleAndOtherInfoList: rule = parallelTitleAndOtherInfo.at_least(1)
        .push { items ->
            NodeList(
                items.flatMap { (it as? NodeList)?.values ?: emptyList() }
            )
        }

    private val parallelTitleFull: rule = seq(
        parallelTitle,
        parallelOtherInfoList.maybe(),
        parallelSORList.maybe()
    ).push { items ->
        NodeList(
            listOf(items[0] as ParallelTitle)
                .plus((items[1] as? NodeList?)?.values ?: emptyList())
                .plus((items[2] as? NodeList?)?.values ?: emptyList())
        )
    }

    private val titleSection: rule = seq(
        title,
        parallelTitleList.maybe(),
        longest(
            otherInfoList,
            seq(otherInfoList, parallelData),
            seq(otherInfoList, parallelTitleAndOtherInfo),
            seq(otherInfoList, parallelTitleAndOtherInfoList)
        ).maybe()
    )

    private val titleList: rule = seq(titleSection).sep(1, semicolon)

    private val titleStatement: rule = seq(
        titleList,
        longest(
            sorList,
            seq(sorList, parallelData),
            seq(sorList, parallelTitleFull)
        ).maybe()
    ).push { items ->
        val titles = mutableListOf<Title>()
        val otherInfos = mutableListOf<OtherInfo>()
        val sors = mutableListOf<SOR>()
        val parallelTitles = mutableListOf<ParallelTitle>()
        val parallelOtherInfos = mutableListOf<ParallelOtherInfo>()
        val parallelSORs = mutableListOf<ParallelSOR>()
        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is Title -> titles.add(it)
                is OtherInfo -> otherInfos.add(it)
                is SOR -> sors.add(it)
                is ParallelTitle -> parallelTitles.add(it)
                is ParallelOtherInfo -> parallelOtherInfos.add(it)
                is ParallelSOR -> parallelSORs.add(it)
            }
        }

        return@push TitleStatementNode(
            titles, otherInfos, sors,
            parallelTitles, parallelOtherInfos, parallelSORs
        )
    }

    private val titleStatementList: rule = seq(titleStatement).sep(2, period)

    private val root: rule = longest(
        titleStatement,
        titleStatementList
    )

    init {
        ws = usual_whitespace
        make_rule_names()
    }

    fun parse(input: String): List<TitleStatementNode> {
        return prepare(input)
            .map { Autumn.parse(root, it, ParseOptions.get()) }
            .firstOrNull { it.full_match }
            ?.let { result ->
                result.value_stack.mapNotNull { it as TitleStatementNode }
            }
            ?: emptyList()
    }

    fun parseAll(input: String): List<List<TitleStatementNode>> {
        return prepare(input)
            .map { Autumn.parse(root, it, ParseOptions.get()) }
            .filter { it.full_match }
            .map { result ->
                result.value_stack.mapNotNull { it as TitleStatementNode }
            }
    }
}