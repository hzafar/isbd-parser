package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.DSL.rule

val TitleStatementGrammar.parallelSeries: rule
    get() = seq(
        equalSign,
        seriesTitle,
        dependentTitleList.maybe(),
        parallelSORList.maybe()
    ).push { items ->
        val seriesEntries = (items[1] as? NodeList)?.values
            ?.map { it as SeriesEntry }
            ?: emptyList()

        val entrySors = (items[2] as? NodeList)?.values
            ?.map { it as ParallelSOR }
            ?: emptyList()

        ParallelSeries(
            seriesTitle = items[0] as SeriesTitle,
            seriesEntry = seriesEntries,
            entrySors = entrySors
        )
    }

val TitleStatementGrammar.parallelSeriesEntryTitle: rule
    get() = seq(
        equalSign,
        data
    ).push { items ->
        items[0] as? String
    }
