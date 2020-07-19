package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import norswap.autumn.DSL.rule

val TitleStatementGrammar.seriesTitle: rule
    get() = seq(data, seriesOtherInfo.maybe(), period)
        .push { items ->
            SeriesTitle(
                title = items[0] as String,
                otherInfo = (items[1] as? SeriesOtherInfo)
                    ?.let { otherInfo -> listOf(otherInfo) }
                    ?: emptyList()
            )
        }

val TitleStatementGrammar.commonDependentTitle: rule
    get() = seq(
        seriesTitle,
        seq(rule(DesignationFromMarcParser()), comma.maybe()).maybe(),
        seriesEntryTitle.maybe()
    ).push { items ->
        SeriesEntry(
            seriesTitle = items[0] as SeriesTitle,
            entryTitle = items[2] as? SeriesEntryTitle,
            designation = items[1] as? SeriesEntryDesignation
        )
    }

val TitleStatementGrammar.seriesRoot: rule
    get() = seq(
        commonDependentTitle,
        parallelCommonDependentTitle.maybe(),
        sorList.maybe(),
        parallelSeriesFull.maybe()
    ).push { items ->
        val sors = mutableListOf<SOR>()
        val parallelTitles = mutableListOf<ParallelSeriesEntry>()
        val parallelSORs = mutableListOf<ParallelSOR>()
        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is SOR -> sors.add(it)
                is ParallelSeriesEntry -> parallelTitles.add(it)
                is ParallelSOR -> parallelSORs.add(it)
            }
        }

        TitleStatement(
            titles = listOf(items[0] as SeriesEntry),
            parallelTitles = parallelTitles,
            sors = sors,
            parallelSORs = parallelSORs
        )
    }

fun TitleStatementGrammar.seriesRootWithMARC(marcData: MARCField): rule {
    val saveMarc: rule = rule(this.SaveMarcData(marcData))
    return seq(saveMarc, seriesRoot)
}