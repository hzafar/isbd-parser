package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A class holding the title part of a title statement.
 *
 * @property titleProper the title proper, or main title.
 * @property otherInfo a list of other title information associated with this title.
 * @property parallelTitles a list of parallel titles associated with this title.
 * @property parallelOtherInfo a list of parallel other title information associated
 * with this title.
 */
data class Title(
    val titleProper: TitleProper,
    val otherInfo: List<OtherInfo> = emptyList(),
    val parallelTitles: List<ParallelTitle> = emptyList(),
    val parallelOtherInfo: List<ParallelOtherInfo> = emptyList()
) : Node()