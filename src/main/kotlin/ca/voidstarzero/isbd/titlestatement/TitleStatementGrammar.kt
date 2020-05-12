package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL
import norswap.autumn.ParseState
import norswap.autumn.parsers.AbstractForwarding
import norswap.autumn.parsers.CharPredicate

abstract class TitleStatementGrammar : DSL() {

    val store: ParseState<TitleParseContext> = ParseState(TitleStatementGrammar::class, ::TitleParseContext)

    private val REPLACE = 0xfffd.toChar()

    private val colon = rule(
        object : AbstractForwarding(
            "colon",
            str("$REPLACE:$REPLACE")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.currentState
                        context.currentState = TitleParseContext.State.OTHER_INFO
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
            str("$REPLACE/$REPLACE")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.currentState
                        context.currentState = TitleParseContext.State.SOR
                        return@apply Runnable {
                            context.currentState = oldState
                        }
                    }
                }.get()
        ) {}
    )

    private val equalSign: rule = str("$REPLACE=$REPLACE")
    private val semicolon: rule = str("$REPLACE;$REPLACE")
    private val period: rule = str(". ")
    private val comma: rule = str(", ")

    private val char = rule(CharPredicate("char") {
        it !in listOf(REPLACE, '\u0000', ' ', '.', '\t', '\n', '\r').map(Char::toInt)
    })

    private val data: rule = char.at_least(1).sep(0, usual_whitespace)
        .push(with_string { _, _, match -> match })

    private val titleProper: rule = data
        .push { items -> TitleProper(items[0] as String) }

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

    private val parallelData = rule(
        object : AbstractForwarding(
            "parallel_data",
            seq(equalSign, data)
                .collect().action_with_string { parse, items, _ ->
                    val context = store.data(parse)
                    val toPush: Any? = when (context.currentState) {
                        TitleParseContext.State.OTHER_INFO -> ParallelOtherInfo(items[0] as String)
                        TitleParseContext.State.SOR -> ParallelSOR(items[0] as String)
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
            val title = items[0] as ParallelTitle
            val otherInfos = (items[1] as NodeList).values.map { it as ParallelOtherInfo }
            ParallelTitle(title.title, otherInfos)
        }

    private val parallelTitleAndOtherInfoList: rule = parallelTitleAndOtherInfo.at_least(1)
        .push { items ->
            NodeList(
                items.filterNotNull().map { it as ParallelTitle }
            )
        }

    private val parallelTitleFull: rule = seq(
        parallelTitle,
        parallelOtherInfoList.maybe(),
        parallelSORList.maybe()
    ).push { items ->
        val title = items[0] as ParallelTitle
        val otherInfos = (items[1] as? NodeList)?.values?.map { it as ParallelOtherInfo } ?: emptyList()
        val sors = (items[2] as? NodeList)?.values?.map { it as ParallelSOR } ?: emptyList()
        ParallelTitle(title.title, otherInfos, sors)
    }

    private val title: rule = seq(
        titleProper,
        parallelTitleList.maybe(),
        longest(
            otherInfoList,
            seq(otherInfoList, parallelData),
            seq(otherInfoList, parallelTitleAndOtherInfo),
            seq(otherInfoList, parallelTitleAndOtherInfoList)
        ).maybe()
    ).push { items ->
        val titleProper = items[0] as TitleProper
        val otherInfos = mutableListOf<OtherInfo>()
        val parallelOtherInfos = mutableListOf<ParallelOtherInfo>()
        val parallelTitles = mutableListOf<ParallelTitle>()

        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is OtherInfo -> otherInfos.add(it)
                is ParallelOtherInfo -> parallelOtherInfos.add(it)
                is ParallelTitle -> parallelTitles.add(it)
            }
        }

        Title(titleProper, otherInfos, parallelTitles, parallelOtherInfos)
    }

    private val titleList: rule = seq(title).sep(1, semicolon)

    private val titleStatement: rule = seq(
        titleList,
        longest(
            sorList,
            seq(sorList, parallelData),
            seq(sorList, parallelTitleFull)
        ).maybe()
    ).push { items ->
        val titles = mutableListOf<Title>()
        val sors = mutableListOf<SOR>()
        val parallelTitles = mutableListOf<ParallelTitle>()
        val parallelSORs = mutableListOf<ParallelSOR>()
        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is Title -> titles.add(it)
                is SOR -> sors.add(it)
                is ParallelTitle -> parallelTitles.add(it)
                is ParallelSOR -> parallelSORs.add(it)
            }
        }

        TitleStatement(titles, sors, parallelTitles, parallelSORs)
    }

    private val titleStatementList: rule = seq(titleStatement).sep(2, period)

    protected val root: rule = longest(
        titleStatement,
        titleStatementList
    )

    init {
        ws = usual_whitespace
        make_rule_names()
    }
}