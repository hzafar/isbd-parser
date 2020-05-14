package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL.rule

val TitleStatementGrammar.titleProper: rule
    get() = data
        .push { items -> TitleProper(items[0] as String) }

val TitleStatementGrammar.title: rule
    get() = seq(
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
        val otherInfo = mutableListOf<OtherInfo>()
        val parallelOtherInfo = mutableListOf<ParallelOtherInfo>()
        val parallelTitles = mutableListOf<ParallelMonograph>()

        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is OtherInfo -> otherInfo.add(it)
                is ParallelOtherInfo -> parallelOtherInfo.add(it)
                is ParallelMonograph -> parallelTitles.add(it)
            }
        }
        Monograph(
            titleProper = titleProper, otherInfo = otherInfo,
            parallelTitles = parallelTitles, parallelOtherInfo = parallelOtherInfo
        )
    }

val TitleStatementGrammar.titleList: rule
    get() = seq(title).sep(1, semicolon)

val TitleStatementGrammar.titleStatement: rule
    get() = seq(
        titleList,
        longest(
            sorList,
            seq(sorList, parallelData),
            seq(sorList, parallelTitleFull)
        ).maybe()
    ).push { items ->
        val titles = mutableListOf<Monograph>()
        val sors = mutableListOf<SOR>()
        val parallelTitles = mutableListOf<ParallelMonograph>()
        val parallelSORs = mutableListOf<ParallelSOR>()
        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is Monograph -> titles.add(it)
                is SOR -> sors.add(it)
                is ParallelMonograph -> parallelTitles.add(it)
                is ParallelSOR -> parallelSORs.add(it)
            }
        }

        TitleStatement(titles, sors, parallelTitles, parallelSORs)
    }

val TitleStatementGrammar.titleStatementList: rule
    get() = seq(titleStatement).sep(2, period)

val TitleStatementGrammar.monographRoot: rule
    get() = longest(
        titleStatement,
        titleStatementList
    )