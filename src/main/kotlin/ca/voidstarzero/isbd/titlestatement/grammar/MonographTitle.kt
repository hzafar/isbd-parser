package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL.rule

/**
 * Matches a string of characters.
 *
 * Pushes the matched string as a [TitleProper] to the parser's value stack.
 */
val TitleStatementGrammar.titleProper: rule
    get() = data
        .push { items -> TitleProper(items[0] as String) }

/**
 * Matches a title with as many parts as possible, including other
 * title info, parallel title, parallel other title info, statements
 * of responsibility, and parallel statements of responsibility.
 *
 * Pushes the result as a [Monograph] title to the parser's value stack.
 */
val TitleStatementGrammar.title: rule
    get() = seq(
        titleProper,
        parallelTitleList.maybe(),
        longest(
            otherInfoList,
            seq(otherInfoList, parallelDataList),
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

/**
 * Matches a sequence of full titles.
 */
val TitleStatementGrammar.titleList: rule
    get() = seq(title).sep(1, semicolon)

/**
 * Matches a full title statement.
 *
 * Pushes the result as a [TitleStatement] to the parser's value stack.
 */
val TitleStatementGrammar.titleStatement: rule
    get() = seq(
        titleList,
        longest(
            sorList,
            seq(sorList, parallelDataList),
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

/**
 * Matches a sequence of full title statements.
 */
val TitleStatementGrammar.titleStatementList: rule
    get() = seq(titleStatement).sep(2, period)

/**
 * The root parser for parsing monograph titles.
 * Matches as many title statements as possible.
 */
val TitleStatementGrammar.monographRoot: rule
    get() = longest(
        titleStatement,
        titleStatementList
    )