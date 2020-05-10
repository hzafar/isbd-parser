package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A class for parse nodes that contain only a string.
 *
 */
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

/**
 * A title proper, or main title.
 *
 */
class TitleProper(value: String) : SimpleNode(value)

/**
 * A string containing other title information, such as a subtitle.
 *
 */
class OtherInfo(value: String) : SimpleNode(value)

/**
 * A statement of responsibility.
 *
 */
class SOR(value: String) : SimpleNode(value)

/**
 * A parallel other title information string.
 *
 */
class ParallelOtherInfo(value: String) : SimpleNode(value)

/**
 * A parallel statement of responsibility.
 *
 */
class ParallelSOR(value: String) : SimpleNode(value)
