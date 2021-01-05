package ca.voidstarzero.isbd.titlestatement.ast

/**
 * Base class for parallel titles.
 */
sealed class ParallelTitle : Node()

/**
 * Class containing a parsed parallel title.
 *
 * @property title the parallel title string.
 * @property otherInfo a list of parallel other title information strings.
 * @property sors a list of parallel statements of responsibility.
 */
data class ParallelMonograph(
    val title: String,
    val otherInfo: List<ParallelOtherInfo> = emptyList(),
    val sors: List<ParallelSOR> = emptyList()
) : ParallelTitle()

/** A class holding a parallel series entry title.
 *
 * @property seriesTitle the parallel title of the series to which this entry belongs.
 * @property seriesEntry the parallel title of the entry.
 */
data class ParallelSeries(
    val seriesTitle: SeriesTitle,
    val seriesEntry: List<SeriesEntry> = emptyList(),
    val entrySors: List<ParallelSOR> = emptyList()
) : ParallelTitle()