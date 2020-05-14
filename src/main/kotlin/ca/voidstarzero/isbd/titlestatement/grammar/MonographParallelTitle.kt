package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.NodeList
import ca.voidstarzero.isbd.titlestatement.ast.ParallelOtherInfo
import ca.voidstarzero.isbd.titlestatement.ast.ParallelSOR
import ca.voidstarzero.isbd.titlestatement.ast.ParallelMonograph
import norswap.autumn.DSL.rule

val TitleStatementGrammar.parallelTitleProper: rule
    get() = seq(equalSign, data)
        .push { items -> ParallelMonograph(items[0] as String) }

val TitleStatementGrammar.parallelTitleList: rule
    get() = parallelTitleProper.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as ParallelMonograph })
        }

val TitleStatementGrammar.parallelTitleAndOtherInfo: rule
    get() = seq(parallelTitleProper, parallelOtherInfoList)
        .push { items ->
            val title = items[0] as ParallelMonograph
            val otherInfo = (items[1] as NodeList).values.map { it as ParallelOtherInfo }

            ParallelMonograph(title = title.title, otherInfo = otherInfo)
        }

val TitleStatementGrammar.parallelTitleAndOtherInfoList: rule
    get() = parallelTitleAndOtherInfo.at_least(1)
        .push { items ->
            NodeList(
                items.filterNotNull().map { it as ParallelMonograph }
            )
        }

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
