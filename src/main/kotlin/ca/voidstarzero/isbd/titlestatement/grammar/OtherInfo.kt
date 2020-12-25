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
 * Pushes the result as a [NodeList] of [OtherInfo] to the parser's value stack.
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
 * Pushes the result as a [NodeList] of [ParallelOtherInfo] to the parser's value stack.
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
val TitleStatementGrammar.seriesTitleOtherInfo: rule
    get() = otherInfoOfType(::SeriesOtherInfo)

/**
 * Matches a list of series other information, as matched by [seriesTitleOtherInfo].
 *
 * Pushes the result as a [NodeList] of [SeriesOtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.seriesTitleOtherInfoList: rule
    get() = seriesTitleOtherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as SeriesOtherInfo })
        }

/**
 * Matches a [TitleStatementGrammar.colon] followed by a string of characters.
 *
 * Pushes the matched data as a [SeriesEntryOtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.seriesEntryOtherInfo: rule
    get() = otherInfoOfType(::SeriesEntryOtherInfo)

/**
 * Matches a list of series entry other information, as matched by [seriesEntryOtherInfo].
 *
 * Pushes the matched data as a [NodeList] of [SeriesEntryOtherInfo] to the parser's value stack.
 */
val TitleStatementGrammar.seriesEntryOtherInfoList: rule
    get() = seriesEntryOtherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as SeriesEntryOtherInfo })
        }