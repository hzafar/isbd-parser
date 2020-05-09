package ca.voidstarzero.isbd.titlestatement.ast

open class SimpleNode(val value: String) : Node() {
    override fun toString(): String {
        return value
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is SimpleNode -> value == other.value
            else -> false
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

class Title(value: String) : SimpleNode(value)
class OtherInfo(value: String) : SimpleNode(value)
class SOR(value: String) : SimpleNode(value)
class ParallelOtherInfo(value: String) : SimpleNode(value)
class ParallelSOR(value: String) : SimpleNode(value)
