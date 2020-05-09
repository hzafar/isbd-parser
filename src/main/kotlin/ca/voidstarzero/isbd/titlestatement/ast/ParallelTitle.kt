package ca.voidstarzero.isbd.titlestatement.ast

class ParallelTitle(
    val title: String,
    val otherInfos: List<ParallelOtherInfo> = emptyList(),
    val sors: List<ParallelSOR> = emptyList()
) : Node() {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is ParallelTitle ->
                title == other.title
                        && otherInfos == other.otherInfos
                        && sors == other.sors
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + otherInfos.hashCode()
        result = 31 * result + sors.hashCode()
        return result
    }
}