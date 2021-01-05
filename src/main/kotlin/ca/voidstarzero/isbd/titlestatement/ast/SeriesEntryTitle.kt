package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A series entry title.
 *
 */
data class SeriesEntryTitle(
    val title: String,
    val parallelTitle: String? = null
)
