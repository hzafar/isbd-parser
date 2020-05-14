package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A series entry title, or "dependent title".
 *
 * @property title the series entry title.
 * @property otherInfo other title information for the series entry.
 *
 */
data class SeriesEntryTitle(
    val title: String,
    val otherInfo: List<SeriesEntryOtherInfo> = emptyList()
) : Node()