package ca.voidstarzero.isbd

import ca.voidstarzero.isbd.ast.*
import norswap.autumn.*
import norswap.autumn.parsers.AbstractForwarding
import norswap.autumn.parsers.CharPredicate
import java.util.*

class Grammar : DSL() {

    val store: ParseState<ParseContext> = ParseState(Grammar::class, ::ParseContext)

    private val openBracket: rule = str("[")
    private val closeBracket: rule = str("]")
    private val equalSign: rule = str("_=_")
    private val colon: rule = str("_:_")
    private val slash: rule = str("_/_")
    private val semicolon: rule = str("_;_")
    private val point: rule = str("._")
    private val comma: rule = str(",_")
    private val char = rule(CharPredicate("char") {
        it !in listOf('\u0000', '_', '[', ']', ' ', '\t', '\n', '\r').map(Char::toInt) // FIXME (i.e, '_')
    })

    private val data = rule(
        object : AbstractForwarding(
            "data",
            char.at_least(1).sep(0, usual_whitespace)
                .collect().action_with_string { parse, _, match ->
                    parse.log.apply {
                        val context = store.data(parse)
                        // context.parsedOtherInfo = true
                        parse.stack.push(match)
                        return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                    }
                }.get()
        ) {}
    )

    private val materialDesignation = rule(
        object : AbstractForwarding(
            "material_designation",
            seq(
                usual_whitespace.maybe(),
                openBracket,
                data,
                closeBracket,
                usual_whitespace.maybe()
            ).collect().action_with_string { parse, items, _ ->
                parse.log.apply {
                    val context = store.data(parse)
                    // context.parsedOtherInfo = true
                    parse.stack.push(MaterialDesignation(items[0] as String))
                    return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                }
            }.get()
        ) {}
    )

    private val otherInfo = rule(
        object : AbstractForwarding(
            "other_title_info",
            seq(colon, data)
                .collect().action_with_string { parse, items, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        // context.parsedOtherInfo = true
                        parse.stack.push(OtherTitleInfo(items[0] as String))
                        return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                    }
                }.get()
        ) {}
    )

    private val title = rule(
        object : AbstractForwarding(
            "title",
            seq(
                data,
                materialDesignation.maybe(),
                otherInfo.maybe()
            ).collect().action_with_string { parse, items, _ ->
                parse.log.apply {
                    val context = store.data(parse)
                    // context.parsedOtherInfo = true
                    parse.stack.push(
                        Title(
                            items[0] as String,
                            items[1] as MaterialDesignation?,
                            items[2] as OtherTitleInfo?
                        )
                    )
                    return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                }
            }.get()
        ) {}
    )

    private val parallelTitle = rule(
        object : AbstractForwarding(
            "parallel_title",
            seq(equalSign, title)
                .collect().action_with_string { parse, items, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        // context.parsedOtherInfo = true
                        parse.stack.push(
                            ParallelTitle(items[0] as Title)
                        )
                        return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                    }
                }.get()
        ) {}
    )

    private val parallelTitleList = rule(
        object : AbstractForwarding(
            "parallel_title_list",
            parallelTitle.at_least(1)
                .collect().action_with_string { parse, items, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        // context.parsedOtherInfo = true
                        parse.stack.push(
                            ParallelTitleList(
                                items.filterNotNull().map { it as ca.voidstarzero.isbd.ast.ParallelTitle }
                            )
                        )
                        return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                    }
                }.get()
        ) {}
    )

    private val additionalSor = rule(
        object : AbstractForwarding(
            "additional_sor",
            seq(semicolon, data).at_least(1)
                .collect().action_with_string { parse, _, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        // context.parsedOtherInfo = true
                        return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                    }
                }.get()
        ) {}
    )

    private val sor = rule(
        object : AbstractForwarding(
            "sor",
            seq(slash, data, additionalSor.maybe())
                .collect().action_with_string { parse, items, _ ->
                    parse.log.apply {
                        val context = store.data(parse)
                        // context.parsedOtherInfo = true
                        parse.stack.push(
                            SOR(items.filterNotNull().map { it as kotlin.String })
                        )
                        return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                    }
                }.get()
        ) {}
    )

    private val titleStatement = rule(
        object : AbstractForwarding(
            "title_statement",
            seq(
                title,
                parallelTitleList.maybe(),
                sor.maybe()
            ).collect().action_with_string { parse, items, _ ->
                parse.log.apply {
                    val context = store.data(parse)
                    // context.parsedOtherInfo = true
                    parse.stack.push(
                        TitleStatement(
                            items[0] as Title,
                            items[1] as ParallelTitleList?,
                            items[2] as SOR?
                        )
                    )
                    return@apply Runnable { /*context.parsedOtherInfo = false*/ }
                }
            }.get()
        ) {}
    )

    private val root: rule = titleStatement

    init {
        ws = usual_whitespace
        make_rule_names()
    }

    private fun prepare(input: String): String {
        return input
            .replace(" = ", "_=_")
            .replace(" : ", "_:_")
            .replace(" / ", "_/_")
            .replace(" ; ", "_;_")
        //.replace(". ", "._")
        //.replace(", ", ",_")
    }

    fun parse(input: String): TitleStatement? {
        val titleString = prepare(input)
        val result = Autumn.parse(root, titleString, ParseOptions.get())

        if (result.full_match) {
            return result.value_stack[0] as TitleStatement
        }

        return null
    }
}