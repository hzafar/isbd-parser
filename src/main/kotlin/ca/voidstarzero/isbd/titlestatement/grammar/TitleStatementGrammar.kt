package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.TitleParseContext
import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import norswap.autumn.DSL
import norswap.autumn.Parse
import norswap.autumn.ParseState
import norswap.autumn.parsers.AbstractForwarding
import norswap.autumn.parsers.AbstractPrimitive
import norswap.autumn.parsers.CharPredicate

abstract class TitleStatementGrammar : DSL() {

    val store: ParseState<TitleParseContext> = ParseState(TitleStatementGrammar::class, ::TitleParseContext)

    private val REPLACE = 0xfffd.toChar()

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

    val equalSign: rule = str("$REPLACE=$REPLACE")
    val semicolon: rule = str("$REPLACE;$REPLACE")
    val period: rule = str(". ")
    val comma: rule = str(", ")

    private val SPECIAL_CHARS = listOf(REPLACE, '\u0000', ' ', '.', '\t', '\n', '\r')

    private val char: rule = rule(CharPredicate("char") {
        it !in SPECIAL_CHARS.map(Char::toInt)
    })

    private val charWithoutComma: rule = rule(CharPredicate("charWithoutComma") {
        it !in SPECIAL_CHARS.plus(',').map(Char::toInt)
    })

    val data: rule = char.at_least(1).sep(0, usual_whitespace)
        .push(with_string { _, _, match -> match })

    val dataWithoutComma: rule = charWithoutComma.at_least(1).sep(0, usual_whitespace)
        .push(with_string { _, _, match -> match })

    val parallelData: rule = rule(
        object : AbstractForwarding(
            "parallel_data",
            seq(equalSign, data)
                .collect().action_with_string { parse, items, _ ->
                    val context = store.data(parse)
                    val toPush: Node? = when (context.currentState) {
                        TitleParseContext.State.OTHER_INFO -> ParallelOtherInfo(items[0] as String)
                        TitleParseContext.State.SOR -> ParallelSOR(items[0] as String)
                        else -> null
                    }
                    parse.stack.push(toPush)
                }.get()
        ) {}
    )

    inner class SaveMarcData(val marcData: MARCField) : AbstractPrimitive("save_marc_data", false) {
        override fun doparse(parse: Parse?): Boolean {
            val context = store.data(parse)
            context.marcData = marcData

            return true;
        }
    }

    val entryTitleOrDesignation: rule
        get() = rule(
            object : AbstractForwarding(
                "entry_title_or_designation",
                seq(data, entryOtherInfo.maybe())
                    .collect().action_with_string { parse, items, _ ->
                        val context = store.data(parse)
                        val marcData = context.marcData
                        val designations: List<String> = marcData?.subfields
                            ?.filter { it.first == 'n' }
                            ?.map { it.second }
                            ?: emptyList()

                        val toPush: Node = when (val data = items[0] as String) {
                            in designations -> SeriesEntryDesignation(data)
                            else -> SeriesEntryTitle(
                                title = data,
                                otherInfo = (items[1] as? SeriesEntryOtherInfo)?.let { listOf(it) } ?: emptyList()
                            )
                        }
                        parse.stack.push(toPush)
                    }.get()
            ) {}
        )

    init {
        ws = usual_whitespace
        make_rule_names()
    }
}