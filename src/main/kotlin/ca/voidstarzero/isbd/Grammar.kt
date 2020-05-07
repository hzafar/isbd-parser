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

    private val equalSign: rule = str("_=_")

    private val colon = rule(
        object : AbstractForwarding(
            "colon",
            str("_:_")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.inState
                        context.inState = ParseContext.State.OTHER_INFO
                        return@apply Runnable {
                            context.inState = oldState
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
                        val oldState = context.inState
                        context.inState = ParseContext.State.SOR
                        return@apply Runnable {
                            context.inState = oldState
                        }
                    }
                }.get()
        ) {}
    )

    private val semicolon: rule = str("_;_")

    private val period: rule = str(". ")

    private val char = rule(CharPredicate("char") {
        it !in listOf('\u0000', '_', ' ', '.', '\t', '\n', '\r').map(Char::toInt) // FIXME
    })

    private val data: rule = char.at_least(1).sep(0, usual_whitespace)
        .push(with_string { parse, _, match -> match })

    private val otherTitleInfo: rule = seq(colon, data)
        .push(with_string { _, items, _ ->
            OtherInfo(items[0] as String)
        })

    private val otherTitleInfoList: rule = otherTitleInfo.at_least(1)
        .push(with_string { _, items, _ ->
            NodeList(items.filterNotNull().mapNotNull { it as OtherInfo })
        })

    private val sor: rule = seq(slash, data)
        .push(with_string { _, items, _ ->
            SOR(items[0] as String)
        })

    private val additionalSor: rule = seq(semicolon, data).at_least(1)
        .push(with_string { _, items, _ ->
            NodeList(items.filterNotNull().mapNotNull { SOR(it as String) })
        })

    private val title: rule = data
        .push(with_string { _, items, _ ->
            Title(items[0] as String)
        })

    private val parallelData = rule(
        object : AbstractForwarding(
            "parallel_data",
            seq(equalSign, data)
                .collect().action_with_string { parse, items, _ ->
                    val context = store.data(parse)
                    val toPush: Any? = when (context.inState) {
                        ParseContext.State.OTHER_INFO -> ParallelOtherInfo(items[0] as String)
                        ParseContext.State.SOR -> ParallelSOR(items[0] as String)
                        else -> null
                    }
                    parse.stack.push(toPush)
                }.get()
        ) {}
    )

    private val parallelOtherTitleInfo: rule = seq(colon, data)
        .push(with_string { _, items, _ -> ParallelOtherInfo(items[0] as String) })

    private val parallelOtherTitleInfoList: rule = parallelOtherTitleInfo.at_least(1)
        .push(with_string { _, items, _ ->
            NodeList(items.filterNotNull().mapNotNull { it as ParallelOtherInfo })
        })

    private val parallelSOR: rule = seq(slash, data)
        .push(with_string { _, items, _ ->
            ParallelSOR(items[0] as String)
        })

    private val parallelAdditionalSor: rule = seq(semicolon, data).at_least(1)
        .push(with_string { _, items, _ ->
            NodeList(items.filterNotNull().mapNotNull { ParallelSOR(it as String) })
        })

    private val parallelTitle: rule = seq(equalSign, data)
        .push(with_string { _, items, _ -> ParallelTitle(items[0] as String) })

    private val parallelTitleList: rule = parallelTitle.at_least(1)
        .push(with_string { _, items, _ ->
            NodeList(items.filterNotNull().mapNotNull { it as ParallelTitle })
        })

    private val parallelTitleAndOtherInfo: rule = seq(parallelTitle, parallelOtherTitleInfoList)
        .push { items -> items }

    private val parallelTitleAndOtherInfoList: rule = parallelTitleAndOtherInfo.at_least(1)
        .push { items ->
            items.flatMap {
                when (it) {
                    is Array<*> -> it.toList()
                    else -> listOf(it)
                }
            }.toTypedArray()
        }

    private val parallelTitleFull: rule = seq(
        parallelTitle,
        parallelOtherTitleInfoList.maybe(),
        seq(parallelSOR, parallelAdditionalSor.maybe()).maybe()
    ).push { items -> items }

    private val titleSection: rule = seq(
        title,
        parallelTitleList.maybe(),
        longest(
            otherTitleInfoList,
            seq(otherTitleInfoList, parallelData),
            seq(otherTitleInfoList, parallelTitleAndOtherInfo),
            seq(otherTitleInfoList, parallelTitleAndOtherInfoList)
        ).maybe()
    )

    private val titleList: rule = seq(titleSection).sep(1, semicolon)

    private val titleStatement: rule = seq(
        titleList,
        longest(
            seq(sor, additionalSor.maybe()),
            seq(sor, additionalSor.maybe(), parallelData),
            seq(sor, additionalSor.maybe(), parallelTitleFull)
        ).maybe()
    ).push(with_string { _, items, _ ->
        val titles = mutableListOf<Title>()
        val otherInfos = mutableListOf<OtherInfo>()
        val sors = mutableListOf<SOR>()
        val parallelTitles = mutableListOf<ParallelTitle>()
        val parallelOtherInfos = mutableListOf<ParallelOtherInfo>()
        val parallelSORs = mutableListOf<ParallelSOR>()
        items.flatMap {
            when (it) {
                is Array<*> -> it.toList().flatMap { it3 ->
                    when (it3) {
                        is NodeList -> it3.values
                        else -> listOf(it3)
                    }
                }
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

        return@with_string TitleStatement(
            titles, otherInfos, sors,
            parallelTitles, parallelOtherInfos, parallelSORs
        )
    })

    private val titleStatementList: rule = seq(titleStatement).sep(2, period)

    private val root: rule = longest(
        titleStatement,
        titleStatementList
    )

    init {
        ws = usual_whitespace
        make_rule_names()
    }

    private fun prepare(input: String): String {
        return input
            .replace(" = ", "_=_")
            .replace(" : ", "_:_")
            .replace(" / ", "_/_")
            .replace(" ; ", "_;_")
            .removeSuffix(".")
            .trim()
    }

    fun parse(input: String): List<TitleStatement> {
        val titleString = prepare(input)
        val result = Autumn.parse(root, titleString, ParseOptions.get())

        return if (result.full_match) {
            result.value_stack.mapNotNull { it as TitleStatement }
        } else {
            emptyList()
        }
    }
}