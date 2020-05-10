package ca.voidstarzero.isbd.titlestatement.ast

/**
 * Class containing a parsed parallel title.
 *
 * @property title the parallel title string.
 * @property otherInfo a list of parallel other title information strings, if present.
 * @property sors a list of parallel statements of responsibility, if present.
 */
data class ParallelTitle(
    val title: String,
    val otherInfo: List<ParallelOtherInfo> = emptyList(),
    val sors: List<ParallelSOR> = emptyList()
) : Node() {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is ParallelTitle ->
                title == other.title
                        && otherInfo == other.otherInfo
                        && sors == other.sors
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + otherInfo.hashCode()
        result = 31 * result + sors.hashCode()
        return result
    }
}