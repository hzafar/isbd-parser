package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import norswap.autumn.DSL.rule

val TitleStatementGrammar.seriesTitle: rule
    get() = seq(data, seriesTitleOtherInfo.maybe(), period.maybe())
        .push { items ->
            SeriesTitle(
                title = items[0] as String,
                otherInfo = (items[1] as? SeriesOtherInfo)
                    ?.let { otherInfo -> listOf(otherInfo) }
                    ?: emptyList()
            )
        }

/**
 * Matches a series entry (dependent) title, which may consist
 * of either a title designation or entry title, or both, followed
 * optionally by a list of statements of responsibility.
 */
val TitleStatementGrammar.dependentTitleWithoutMarc: rule
    get() = seq(
        dataWithoutComma,
        seq(comma, data).maybe(),
        seriesEntryOtherInfoList.maybe(),
        period.maybe()
    ).maybe()
        .push { items ->
            val designation = if (items[1] == null) {
                null
            } else {
                items[0]?.let { SeriesEntryDesignation(it as String) }
            }
            val title = items[1]?.let { SeriesEntryTitle(it as String) }
                ?: items[0]?.let { SeriesEntryTitle(it as String) }

            val otherInfo = (items[2] as? NodeList)?.values
                ?.map { it as SeriesEntryOtherInfo }
                ?: emptyList()

            SeriesEntry(
                title = title,
                designation = designation,
                otherInfo = otherInfo
            )
        }

val TitleStatementGrammar.dependentTitle: rule
    get() = seq(
        rule(SeriesEntryFromMarc()),
        seriesEntryOtherInfoList.maybe()
    ).push { items ->
        val title = items[0] as? SeriesEntryTitle
        val designation = items[1] as? SeriesEntryDesignation
        val otherInfo = (items[2] as? NodeList)?.values
            ?.map { it as SeriesEntryOtherInfo }
            ?: emptyList()

        SeriesEntry(
            title = title,
            designation = designation,
            otherInfo = otherInfo
        )
    }

val TitleStatementGrammar.dependentTitleList: rule
    get() = seq(
        longest(
            dependentTitle,
            dependentTitleWithoutMarc
        ).at_least(1)
    ).push { items ->
        NodeList(
            items.filterNotNull().map { it as SeriesEntry }
        )
    }

val TitleStatementGrammar.series: rule
    get() = seq(
        seriesTitle,
        sorList.maybe(),
        dependentTitleList.maybe(),
        sorList.maybe()
    ).push { items ->
        val seriesEntries = (items[2] as? NodeList)?.values
            ?.map { it as SeriesEntry }
            ?: emptyList()

        val entrySors = (items[3] as? NodeList)?.values
            ?.map { it as SOR }
            ?: emptyList()

        Series(
            seriesTitle = items[0] as SeriesTitle,
            seriesEntry = seriesEntries,
            entrySors = entrySors
        )
    }

val TitleStatementGrammar.seriesRoot: rule
    get() = seq(
        series,
        parallelSeries.maybe()
    ).push { items ->
        val parallelTitles = mutableListOf<ParallelSeries>()
        items.flatMap {
            when (it) {
                is NodeList -> it.values
                else -> listOf(it)
            }
        }.forEach {
            when (it) {
                is ParallelSeries -> parallelTitles.add(it)
            }
        }

        TitleStatement(
            titles = listOf(items[0] as Series),
            parallelTitles = parallelTitles
        )
    }

fun TitleStatementGrammar.seriesRootWithMARC(marcData: MARCField): rule {
    val saveMarc: rule = rule(this.SaveMarcData(marcData))
    return seq(saveMarc, seriesRoot)
}