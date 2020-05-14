package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL.rule

val TitleStatementGrammar.otherInfo: rule
    get() = seq(colon, data)
        .push { items -> OtherInfo(items[0] as String) }

val TitleStatementGrammar.otherInfoList: rule
    get() = otherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as OtherInfo })
        }

val TitleStatementGrammar.parallelOtherInfo: rule
    get() = seq(colon, data)
        .push { items -> ParallelOtherInfo(items[0] as String) }

val TitleStatementGrammar.parallelOtherInfoList: rule
    get() = parallelOtherInfo.at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as ParallelOtherInfo })
        }

val TitleStatementGrammar.seriesOtherInfo: rule
    get() = seq(colon, data)
        .push { items -> SeriesOtherInfo(items[0] as String) }

val TitleStatementGrammar.entryOtherInfo: rule
    get() = seq(colon, data)
        .push { items -> SeriesEntryOtherInfo(items[0] as String) }
