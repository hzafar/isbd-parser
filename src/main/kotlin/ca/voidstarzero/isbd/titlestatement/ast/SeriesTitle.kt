package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A series title, or "common title".
 *
 * @property title the title of the series
 * @property otherInfo other title information for the series.
 * @property sors a list of statements of responsibility for the series.
 *
 */
data class SeriesTitle(
    val title: String,
    val otherInfo: List<SeriesOtherInfo> = emptyList()
) : Node()