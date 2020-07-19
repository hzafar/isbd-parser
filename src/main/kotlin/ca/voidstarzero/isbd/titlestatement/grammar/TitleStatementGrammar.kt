package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.TitleParseContext
import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import norswap.autumn.DSL
import norswap.autumn.Parse
import norswap.autumn.ParseState
import norswap.autumn.Parser
import norswap.autumn.parsers.AbstractForwarding
import norswap.autumn.parsers.AbstractPrimitive
import norswap.autumn.parsers.CharPredicate

abstract class TitleStatementGrammar : DSL() {

    val store: ParseState<TitleParseContext> = ParseState(TitleStatementGrammar::class, ::TitleParseContext)

    private val REPLACE = 0xfffd.toChar()

    /**
     * Matches the exact string " : " and sets the current parse context state to OTHER_INFO.
     *
     * This can be used by subsequent parses to interpret whether parallel data is parallel
     * other information, or some other kind of data.
     */
    val colon: rule = rule(
        object : AbstractForwarding(
            "colon",
            str("$REPLACE:$REPLACE")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.currentState
                        context.currentState =
                            TitleParseContext.State.OTHER_INFO
                        return@apply Runnable {
                            context.currentState = oldState
                        }
                    }
                }.get()
        ) {}
    )

    /**
     * Matches the exact string " / " and sets the current parse context state to SOR.
     *
     * This can be used by subsequent parses to interpret whether parallel data is a parallel
     * statement of responsibility, or some other kind of data.
     */
    val slash: rule = rule(
        object : AbstractForwarding(
            "slash",
            str("$REPLACE/$REPLACE")
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        val oldState = context.currentState
                        context.currentState =
                            TitleParseContext.State.SOR
                        return@apply Runnable {
                            context.currentState = oldState
                        }
                    }
                }.get()
        ) {}
    )

    /**
     * Matches the string " = " exactly.
     */
    val equalSign: rule = str("$REPLACE=$REPLACE")

    /**
     * Matches the string " ; " exactly.
     */
    val semicolon: rule = str("$REPLACE;$REPLACE")

    /**
     * Matches the string ". " exactly.
     */
    val period: rule = str(". ")

    /**
     * Matches the string ", " exactly.
     */
    val comma: rule = str(", ")

    val SPECIAL_CHARS = listOf(REPLACE, '\u0000', ' ', '.', '\t', '\n', '\r')

    val char: rule = rule(CharPredicate("char") {
        it !in SPECIAL_CHARS.map(Char::toInt)
    })

    val charWithoutComma: rule = rule(CharPredicate("charWithoutComma") {
        it !in SPECIAL_CHARS.plus(',').map(Char::toInt)
    })

    /**
     * Matches a string made up of any characters except those in [SPECIAL_CHARS].
     *
     * Pushes the matched string onto the parser's value stack.
     */
    val data: rule = char.at_least(1).sep(0, usual_whitespace)
        .push(with_string { _, _, match -> match })

    /**
     * Matches a string made up of any characters except commas and those in [SPECIAL_CHARS].
     *
     * Pushes the matched string onto the parser's value stack.
     */
    val dataWithoutComma: rule = charWithoutComma.at_least(1).sep(0, usual_whitespace)
        .push(with_string { _, _, match -> match })

    /**
     * Matches a sequence of [equalSign] followed by a string of characters.
     *
     * Uses the current parse state to determine if the matched strings are other title
     * information or statements of responsibility. Pushes a [NodeList] of either
     * [ParallelOtherInfo] or [ParallelSOR] to the parser's value stack accordingly.
     */
    val parallelDataList: rule = rule(
        object : AbstractForwarding(
            "parallel_data",
            seq(equalSign, data).at_least(1)
                .collect().action_with_string { parse, items, _ ->
                    val context = store.data(parse)
                    val toPush: NodeList? = when (context.currentState) {
                        TitleParseContext.State.OTHER_INFO ->
                            NodeList(items.filterNotNull().map { ParallelOtherInfo(it as String) })
                        TitleParseContext.State.SOR ->
                            NodeList(items.filterNotNull().map { ParallelSOR(it as String) })
                        else -> null
                    }
                    parse.stack.push(toPush)
                }.get()
        ) {}
    )

    /**
     * A generic function that defines a parser to match a [colon] followed
     * by a string of characters and push the result as the given AST node
     * type to the parser's value stack.
     */
    fun <T : Node> otherInfoOfType(nodeConstructor: (String) -> T): rule {
        return seq(colon, data)
            .push { items -> nodeConstructor(items[0] as String) }
    }

    /**
     * A generic function that defines a parser to match a [slash] followed
     * by a string of characters and push the result as the given AST node type
     * to the parser's value stack.
     */
    fun <T : Node> sorOfType(nodeConstructor: (String) -> T): rule {
        return seq(slash, data)
            .push { items -> nodeConstructor(items[0] as String) }
    }

    /**
     * A generic function that defines a parser to match a sequence of strings
     * seperated by [semicolon] and push the result as the given AST node type
     * to the parser's value stack.
     */
    fun <T : Node> additionalSOROfType(nodeConstructor: (String) -> T): rule {
        return seq(semicolon, data).at_least(1)
            .push { items ->
                NodeList(items.filterNotNull().map { nodeConstructor(it as String) })
            }
    }

    /**
     * A custom parser whose purpose is to save the given MARC data in the parser's context
     * so that it can be referenced during the parse. It doesn't consume any input and always
     * "matches".
     */
    inner class SaveMarcData(val marcData: MARCField) : AbstractPrimitive("save_marc_data", false) {
        override fun doparse(parse: Parse?): Boolean {
            val context = store.data(parse)
            context.marcData = marcData

            return true;
        }
    }

    inner class DesignationFromMarcParser : Parser() {
        override fun children(): MutableIterable<Parser> {
            return mutableListOf()
        }

        override fun doparse(parse: Parse): Boolean {
            val context = store.data(parse)

            val marcData = context.marcData
            val designations: List<String> = marcData?.subfields
                ?.filter { it.first == 'n' }
                ?.map { it.second }
                ?: emptyList()

            var pos = parse.pos
            while (pos < parse.string.length
                && !parse.match(pos, ",")
                && !parse.match(pos, REPLACE.toString()))
            {
                pos += 1
            }

            val data = parse.string.substring(parse.pos, pos)
            if (designations.any { it.startsWith(data) }) {
                parse.pos = pos
                parse.stack.push(SeriesEntryDesignation(data))
                return true
            }

            return false
        }

        override fun toStringFull(): String {
            return "designation_from_marc"
        }
    }

    /**
     * Matches a string of characters optionally followed by [colon] and another
     * string.
     *
     * Uses the MARC data, if available, to determine whether the first matched
     * string is a part number / section of work, in which case the value is pushed
     * as a [SeriesEntryDesignation] to the parser's value stack. If it is not a
     * part number / section of work according to the MARC data, the matched values
     * are pushed as a [SeriesEntryTitle].
     */
    val seriesEntryTitle: rule
        get() = seq(data, entryOtherInfo.maybe())
            .push { items ->
                SeriesEntryTitle(
                    title = items[0] as String,
                    otherInfo = (items[1] as? SeriesEntryOtherInfo)?.let { listOf(it) } ?: emptyList()
                )
            }
    init {
        ws = usual_whitespace
        make_rule_names()
    }
}