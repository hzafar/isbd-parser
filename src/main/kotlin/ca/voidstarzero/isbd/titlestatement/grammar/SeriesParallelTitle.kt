package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.NodeList
import ca.voidstarzero.isbd.titlestatement.ast.ParallelSeries
import ca.voidstarzero.isbd.titlestatement.ast.SeriesEntry
import ca.voidstarzero.isbd.titlestatement.ast.SeriesTitle
import norswap.autumn.DSL.rule

val TitleStatementGrammar.parallelCommonDependentTitle: rule
    get() = seq(
        equalSign,
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
        ParallelSeries(
            seriesTitle = items[0] as SeriesTitle,
            seriesEntry = seriesEntries
        )
    }

val TitleStatementGrammar.parallelSeriesFull: rule
    get() = seq(
        parallelCommonDependentTitle,
        parallelSORList
    ).push { items ->
        val entry = items[0] as ParallelSeries
        NodeList(
            values = listOf(entry).plus((items[1] as NodeList).values)
        )
    }