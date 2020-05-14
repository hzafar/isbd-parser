package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL.rule

val TitleStatementGrammar.parallelCommonDependentTitle: rule
    get() = seq(
        equalSign,
        seriesTitle,
        designation.maybe(),
        entryTitleOrDesignation.maybe()
    ).push { items ->
        ParallelSeriesEntry(
            seriesTitle = items[0] as SeriesTitle,
            entryTitle = items[2] as? SeriesEntryTitle,
            designation = items[1] as? SeriesEntryDesignation ?: items[2] as? SeriesEntryDesignation
        )
    }

val TitleStatementGrammar.parallelSeriesFull: rule
    get() = seq(
        parallelCommonDependentTitle,
        parallelSORList
    ).push { items ->
        val entry = items[0] as ParallelSeriesEntry
        NodeList(
            values = listOf(
                ParallelSeriesEntry(
                    seriesTitle = entry.seriesTitle,
                    entryTitle = entry.entryTitle,
                    designation = entry.designation
                )
            ).plus((items[1] as NodeList).values)
        )
    }
