package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.cleanInput
import ca.voidstarzero.isbd.prepare
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatement
import ca.voidstarzero.isbd.titlestatement.grammar.TitleStatementGrammar
import ca.voidstarzero.isbd.titlestatement.grammar.monographRoot
import ca.voidstarzero.isbd.titlestatement.grammar.seriesRoot
import ca.voidstarzero.isbd.titlestatement.grammar.seriesRootWithMARC
import ca.voidstarzero.marc.MARCField
import norswap.autumn.Autumn
import norswap.autumn.ParseOptions

/**
 * Parser class for title statements.
 */
class TitleStatementParser : TitleStatementGrammar() {
    /**
     * Parses the [input] title statement string into a list of [TitleStatement]s.
     *
     * @param input the string containing title data.
     * @return the first parse that consumes the entire input.
     */
    fun parse(input: String): List<TitleStatement> {
        return prepare(input)
            .map { Autumn.parse(monographRoot, it, ParseOptions.get()) }
            .firstOrNull { it.full_match }
            ?.let { result ->
                result.value_stack.mapNotNull { it as TitleStatement }
            }
            ?: emptyList()
    }

    /**
     * Parses the [input] serial title statement string into a list of [TitleStatement]s.
     *
     * @param input the string containing the title data.
     * @param marc the MARC data for this field.
     * @return the first parse that consumes the entire input.
     */
    fun parseSerial(
        input: String,
        marc: MARCField? = null
    ): List<TitleStatement> {
        val parseRoot: rule = marc?.let { seriesRootWithMARC(it) } ?: seriesRoot
        val result = Autumn.parse(parseRoot, cleanInput(input), ParseOptions.get())

        if (result.full_match) {
            return result.value_stack.mapNotNull { it as TitleStatement }
        }

        return emptyList()

    }

    /**
     * Parses the [input] title statement string into a list of [TitleStatement]s.
     *
     * @param input the string containing title data.
     * @return the first parse that consumes the entire input and is likely to
     * be a correct parse. See [goodParse] for details.
     */
    fun parseHeuristically(input: String): List<TitleStatement> {
        return parseAll(input).firstOrNull { goodParse(it) } ?: emptyList()
    }

    /** Produces all possible parses of the [input] title statement string.
     *
     * @param input the string containing title data.
     * @return all parses that consume the entire input.
     */
    fun parseAll(input: String): List<List<TitleStatement>> {
        return prepare(input)
            .map { Autumn.parse(monographRoot, it, ParseOptions.get()) }
            .filter { it.full_match }
            .map { result ->
                result.value_stack.mapNotNull { it as TitleStatement }
            }
    }
}