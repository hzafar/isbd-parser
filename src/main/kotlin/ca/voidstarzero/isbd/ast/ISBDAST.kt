package ca.voidstarzero.isbd.ast

sealed class Node(val value: String) {
    override fun toString(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Node -> value == other.value
            else -> false
        }
    }
}

class Title(value: String) : Node(value)
class OtherInfo(value: String) : Node(value)
class SOR(value: String) : Node(value)
class ParallelTitle(value: String) : Node(value)
class ParallelOtherInfo(value: String) : Node(value)
class ParallelSOR(value: String) : Node(value)

class NodeList(val values: List<Node>)

data class TitleStatement(
    val titles: List<Title> = listOf(),
    val otherInfos: List<OtherInfo> = listOf(),
    val sors: List<SOR> = listOf(),
    val parallelTitles: List<ParallelTitle> = listOf(),
    val parallelOtherInfos: List<ParallelOtherInfo> = listOf(),
    val parallelSORs: List<ParallelSOR> = listOf()
)