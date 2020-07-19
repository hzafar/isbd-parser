package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.NodeList
import ca.voidstarzero.isbd.titlestatement.ast.ParallelOtherInfo
import ca.voidstarzero.isbd.titlestatement.ast.ParallelSOR
import ca.voidstarzero.isbd.titlestatement.ast.ParallelMonograph
import norswap.autumn.DSL.rule

/**
 * Matches an [TitleStatementGrammar.equalSign] followed by a string of characters.
 *
 * Pushes the matched value as a [ParallelMonograph] title to the parser's value stack.
 */
val TitleStatementGrammar.parallelTitleProper: rule
    get() = seq(equalSign, data)
        .push { items -> ParallelMonograph(items[0] as String) }

/**
 * Matches a sequence of [parallelTitleProper]s.
 *
 * Pushes the result as a [NodeList] to the parser's value stack.
 */
val TitleStatementGrammar.parallelTitleList: rule
    get() = parallelTitleProper.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as ParallelMonograph })
        }

/**
 * Matches a [parallelTitleProper] followed by a list of parallel
 * other title info, as matched by [parallelOtherInfoList].
 *
 * Pushes the matched values as a [ParallelMonograph] to the parser's value stack.
 */
val TitleStatementGrammar.parallelTitleAndOtherInfo: rule
    get() = seq(parallelTitleProper, parallelOtherInfoList)
        .push { items ->
            val title = items[0] as ParallelMonograph
            val otherInfo = (items[1] as NodeList).values.map { it as ParallelOtherInfo }

            ParallelMonograph(title = title.title, otherInfo = otherInfo)
        }

/**
 * Matches a sequence of parallel titles with other title information,
 * as matched by [parallelTitleAndOtherInfo].
 *
 * Pushes all matched titles as a [NodeList] of [ParallelMonograph] titles.
 */
val TitleStatementGrammar.parallelTitleAndOtherInfoList: rule
    get() = parallelTitleAndOtherInfo.at_least(1)
        .push { items ->
            NodeList(
                items.filterNotNull().map { it as ParallelMonograph }
            )
        }

/**
 * Matches a parallel title with as many parts (other title info, statement
 * of responsibility) as possible.
 *
 * Pushes the result as a [ParallelMonograph] title to the parser's value stack.
 */
val TitleStatementGrammar.parallelTitleFull: rule
    get() = seq(
        parallelTitleProper,
        parallelOtherInfoList.maybe(),
        parallelSORList.maybe()
    ).push { items ->
        val title = items[0] as ParallelMonograph
        val otherInfo = (items[1] as? NodeList)?.values?.map { it as ParallelOtherInfo } ?: emptyList()
        val sors = (items[2] as? NodeList)?.values?.map { it as ParallelSOR } ?: emptyList()

        ParallelMonograph(title = title.title, otherInfo = otherInfo, sors = sors)
    }
