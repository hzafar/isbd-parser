package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import norswap.autumn.DSL.rule

val TitleStatementGrammar.seriesTitle: rule
    get() = seq(data, seriesTitleOtherInfo.maybe(), sorList.maybe(), period)
        .push { items ->
            val sors = (items[2] as? NodeList)?.values?.map { it as SOR } ?: emptyList()
            SeriesTitle(
                title = items[0] as String,
                otherInfo = (items[1] as? SeriesOtherInfo)
                    ?.let { otherInfo -> listOf(otherInfo) }
                    ?: emptyList(),
                sors = sors
            )
        }

/**
 * Matches a string of characters as a series entry title.
 */
val TitleStatementGrammar.seriesEntryTitle: rule
    get() = data
        .push { items ->
            var result = items[0] as String
            if (result.isNotBlank()) {
                return@push SeriesEntryTitle(result)
            }

            return@push null
        }

/**
 * Matches a series entry (dependent) title, which may consist
 * of either a title designation or entry title, or both, followed
 * optionally by a list of statements of responsibility.
 */
val TitleStatementGrammar.dependentTitle: rule
    get() = seq(
        rule(SeriesEntryDesignationFromMarc()).maybe(),
        seq(seriesEntryTitle, seriesEntryOtherInfoList.maybe()).maybe(),
        sorList.maybe()
    ).push { items ->
        val otherInfoList = (items[2] as? NodeList)?.values?.map { it as SeriesEntryOtherInfo } ?: emptyList()
        val sorList = (items[3] as? NodeList)?.values?.map { it as SOR } ?: emptyList()
        SeriesEntry(
            title = items[1] as? SeriesEntryTitle,
            otherInfo = otherInfoList,
            designation = items[0] as? SeriesEntryDesignation,
            sors = sorList
        )
    }

val TitleStatementGrammar.hierarchicalSeriesEntryTitles: rule
    get() = seq(dependentTitle, period).at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { it as SeriesEntry })
        }

val TitleStatementGrammar.series: rule
    get() = seq(
        seriesTitle,
        hierarchicalSeriesEntryTitles.maybe(),
        dependentTitle.maybe()
    ).push { items ->
        val seriesEntries = mutableListOf<SeriesEntry>()
            .plus((items[1] as? NodeList)?.values
                ?.map { it as SeriesEntry } ?: emptyList())
            .plus((items[2] as? SeriesEntry)
                ?.let { listOf(it) } ?: emptyList()
            )
        Series(
            seriesTitle = items[0] as SeriesTitle,
            seriesEntry = seriesEntries
        )
    }

val TitleStatementGrammar.seriesRoot: rule
    get() = seq(
        series,
        parallelCommonDependentTitle.maybe(),
        sorList.maybe(),
        parallelSeriesFull.maybe()
    ).push { items ->
        val sors = mutableListOf<SOR>()
        val parallelTitles = mutableListOf<ParallelSeries>()
        val parallelSORs = mutableListOf<ParallelSOR>()
        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is SOR -> sors.add(it)
                is ParallelSeries -> parallelTitles.add(it)
                is ParallelSOR -> parallelSORs.add(it)
            }
        }

        TitleStatement(
            titles = listOf(items[0] as Series),
            parallelTitles = parallelTitles,
            sors = sors,
            parallelSORs = parallelSORs
        )
    }

fun TitleStatementGrammar.seriesRootWithMARC(marcData: MARCField): rule {
    val saveMarc: rule = rule(this.SaveMarcData(marcData))
    return seq(saveMarc, seriesRoot)
}