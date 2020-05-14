package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A series title, or "common title".
 *
 * @property title the title of the series
 * @property otherInfo other title information for the series.
 *
 */
data class SeriesTitle(
    val title: String,
    val otherInfo: List<SeriesOtherInfo> = emptyList()
) : Node()