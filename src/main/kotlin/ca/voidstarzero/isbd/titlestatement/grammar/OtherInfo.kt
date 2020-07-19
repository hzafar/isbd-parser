package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL.rule

/**
 * Matches a [TitleStatementGrammar.colon] followed by a string of characters.
 *
 * Pushes the matched data as an [OtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.otherInfo: rule
    get() = otherInfoOfType(::OtherInfo)

/**
 * Matches a sequence of [otherInfo].
 *
 * Pushes the result as a [NodeList] of [OtherInfo].
 */
val TitleStatementGrammar.otherInfoList: rule
    get() = otherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as OtherInfo })
        }

/**
 * Matches a [TitleStatementGrammar.colon] followed by a string of characters.
 *
 * Pushes the result as a [ParallelOtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.parallelOtherInfo: rule
    get() = otherInfoOfType(::ParallelOtherInfo)

/**
 * Matches a list of parallel other information, as matched by [parallelOtherInfo].
 *
 * Pushes the result as a [NodeList] of [ParallelOtherInfo].
 */
val TitleStatementGrammar.parallelOtherInfoList: rule
    get() = parallelOtherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as ParallelOtherInfo })
        }

/**
 * Matches a [TitleStatementGrammar.colon] followed by a string of characters.
 *
 * Pushes the matched data as a [SeriesOtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.seriesOtherInfo: rule
    get() = otherInfoOfType(::SeriesOtherInfo)

/**
 * Matches a [TitleStatementGrammar.colon] followed by a string of characters.
 *
 * Pushes the matched data as a [SeriesEntryOtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.entryOtherInfo: rule
    get() = otherInfoOfType(::SeriesEntryOtherInfo)
