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
 * @property entryTitle the parallel title of the entry.
 * @property designation the parallel part number or designation of the series entry.
 * @property sors a list of parallel statements of responsibility for the series entry.
 */
data class ParallelSeriesEntry(
    val seriesTitle: SeriesTitle,
    val entryTitle: SeriesEntryTitle? = null,
    val designation: SeriesEntryDesignation? = null
) : ParallelTitle()