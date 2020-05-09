package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.prepare
import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.Autumn
import norswap.autumn.ParseOptions

/**
 * Parser class for title statements.
 */
class TitleStatement : TitleStatementGrammar() {

    /**
     * Parses the [input] title statement string into a list of [TitleStatementNode]s.
     *
     * @return the first parse that consumes the entire input.
     */
    fun parse(input: String): List<TitleStatementNode> {
        return prepare(input)
            .map { Autumn.parse(root, it, ParseOptions.get()) }
            .firstOrNull { it.full_match }
            ?.let { result ->
                result.value_stack.mapNotNull { it as TitleStatementNode }
            }
            ?: emptyList()
    }

    /**
     * Parses the [input] title statement string into a list of [TitleStatementNode]s.
     *
     * @return the first parse that consumes the entire input and is likely to
     * be a correct parse. See [goodParse] for details.
     */
    fun parseHeuristically(input: String): List<TitleStatementNode> {
        return parseAll(input).firstOrNull { goodParse(it) } ?: emptyList()
    }

    /** Produces all possible parses of the [input] title statement string.
     *
     * @return all parses that consume the entire input.
     */
    fun parseAll(input: String): List<List<TitleStatementNode>> {
        return prepare(input)
            .map { Autumn.parse(root, it, ParseOptions.get()) }
            .filter { it.full_match }
            .map { result ->
                result.value_stack.mapNotNull { it as TitleStatementNode }
            }
    }
}