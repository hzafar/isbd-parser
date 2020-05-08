package ca.voidstarzero.isbd.titlestatement.ast

sealed class SimpleNode(val value: String) {
    override fun toString(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SimpleNode -> value == other.value
            else -> false
        }
    }
}

class Title(value: String) : SimpleNode(value)
class OtherInfo(value: String) : SimpleNode(value)
class SOR(value: String) : SimpleNode(value)
class ParallelTitle(value: String) : SimpleNode(value)
class ParallelOtherInfo(value: String) : SimpleNode(value)
class ParallelSOR(value: String) : SimpleNode(value)