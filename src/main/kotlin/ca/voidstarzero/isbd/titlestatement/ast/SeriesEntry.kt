package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A series entry title, or "dependent title".
 *
 * @property title the series entry title.
 * @property designation the series entry designation.
 * @property otherInfo other title information for the series entry.
 * @property sors a list of statements of responsibility for the series entry.
 */
data class SeriesEntry(
    val title: SeriesEntryTitle? = null,
    val designation: SeriesEntryDesignation? = null,
    val otherInfo: List<SeriesEntryOtherInfo> = emptyList(),
    val sors: List<SOR> = emptyList()
) : Node()