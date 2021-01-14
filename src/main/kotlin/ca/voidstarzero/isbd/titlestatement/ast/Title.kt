package ca.voidstarzero.isbd.titlestatement.ast

import com.fasterxml.jackson.annotation.JsonTypeInfo

/**
 * Base class for titles.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type"
)
sealed class Title() : Node() {
    val type = this::class.java.simpleName
}

/**
 * A class holding the title part of a title statement.
 *
 * @property titleProper the title proper, or main title, if this is not a series entry.
 * @property otherInfo a list of other title information associated with this title.
 * @property parallelTitles a list of parallel titles associated with this title.
 * @property parallelOtherInfo a list of parallel other title information associated
 * with this title.
 */
data class Monograph(
    val titleProper: TitleProper,
    val otherInfo: List<OtherInfo> = emptyList(),
    val parallelTitles: List<ParallelMonograph> = emptyList(),
    val parallelOtherInfo: List<ParallelOtherInfo> = emptyList()
) : Title()

/**
 * A class holding elements of a series entry title.
 *
 * @property seriesTitle the title of the series to which this entry belongs.
 * @property seriesEntry a hierarchical list of entry parts.
 */
data class Series(
    val seriesTitle: SeriesTitle,
    val seriesEntry: List<SeriesEntry> = emptyList(),
    val entrySors: List<SOR> = emptyList()
) : Title()