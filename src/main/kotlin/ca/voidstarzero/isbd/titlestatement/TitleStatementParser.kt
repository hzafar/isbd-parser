package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.cleanInput
import ca.voidstarzero.isbd.prepare
import ca.voidstarzero.isbd.simpleParse
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatement
import ca.voidstarzero.isbd.titlestatement.grammar.TitleStatementGrammar
import ca.voidstarzero.isbd.titlestatement.grammar.monographRoot
import ca.voidstarzero.isbd.titlestatement.grammar.seriesRootWithMARC
import ca.voidstarzero.isbd.usesISBD
import ca.voidstarzero.marc.MARCField
import ca.voidstarzero.marc.fieldData
import norswap.autumn.Autumn
import norswap.autumn.ParseOptions

/**
 * Parser class for title statements.
 */
class TitleStatementParser : TitleStatementGrammar() {
    /**
     * Parses the given title statement string into a list of [TitleStatement]s.
     *
     * @param input the title statement to parse.
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
     * Parses the given title statement MARC field into a list of [TitleStatement]s.
     *
     * @param marc the MARC field to parse.
     * @return the first parse that consumes the entire input.
     */
    fun parse(marc: MARCField): List<TitleStatement> {
        if (!usesISBD(marc)) {
            return listOf(simpleParse(marc))
        }

        return parse(marc.fieldData())
    }

    /**
     * Parses the given serial title statement MARC field into a list of [TitleStatement]s.
     *
     * @param marc the MARC field to parse.
     * @return the first parse that consumes the entire input.
     */
    fun parseSerial(marc: MARCField): List<TitleStatement> {
        if (!usesISBD(marc)) {
            return listOf(simpleParse(marc))
        }

        val parseRoot: rule = seriesRootWithMARC(marc)
        val result = Autumn.parse(parseRoot, cleanInput(marc.fieldData()), ParseOptions.get())
        if (result.full_match) {
            return result.value_stack.mapNotNull { it as TitleStatement }
        }

        throw Exception(result.toString())
    }

    /** Produces all possible parses of the given title statement string.
     *
     * @param input the title statement to parse.
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

    /** Produces all possible parses of the given title statement MARC field.
     *
     * @param marc the MARC field to parse.
     * @return all parses that consume the entire input.
     */
    fun parseAll(marc: MARCField): List<List<TitleStatement>> {
        return parseAll(marc.fieldData())
    }

    /** Produces all possible parses of the given serial title statement MARC field.
     *
     * @param marc the MARC field to parse.
     * @return all parses that consume the entire input.
     */
    fun parseAllSerial(marc: MARCField): List<List<TitleStatement>> {
        val parseRoot: rule = seriesRootWithMARC(marc)
        return prepare(marc.fieldData())
            .map { Autumn.parse(parseRoot, it, ParseOptions.get()) }
            .filter { it.full_match }
            .map { result ->
                result.value_stack.mapNotNull { it as TitleStatement }
            }
    }

    /**
     * Parses the given title statement string into a list of [TitleStatement]s.
     *
     * @param input the title statement to parse.
     * @return the first parse that consumes the entire input and is likely to
     * be a correct parse. See [goodParse] for details.
     */
    fun parseHeuristically(input: String): List<TitleStatement> {
        return parseAll(input).firstOrNull { goodParse(it) } ?: emptyList()
    }

    /**
     * Parses the given title statement MARC field into a list of [TitleStatement]s.
     *
     * @param marc the MARC field to parse.
     * @return the first parse that consumes the entire input and is likely to
     * be a correct parse. See [goodParse] for details.
     */
    fun parseHeuristically(marc: MARCField): List<TitleStatement> {
        return parseAll(marc).firstOrNull { goodParse(it) } ?: emptyList()
    }
}