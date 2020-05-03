package ca.voidstarzero.isbd

import ca.voidstarzero.isbd.ast.*
import norswap.autumn.Autumn
import norswap.autumn.DSL
import norswap.autumn.ParseOptions
import norswap.autumn.parsers.CharPredicate

class Grammar : DSL() {
    private val openBracket: rule = str("[")
    private val closeBracket: rule = str("]")
    private val equalSign: rule = str("_=_")
    private val colon: rule = str("_:_")
    private val slash: rule = str("_/_")
    private val semicolon: rule = str("_;_")
    private val point: rule = str("._")
    private val comma: rule = str(",_")
    private val char = rule(CharPredicate("char") {
        it !in listOf('\u0000', '_', '[', ']', ' ', '\t', '\n', '\r').map(Char::toInt)
    })

    private val data: rule = char.at_least(1).sep(0, usual_whitespace)
        .push(with_string { parse, items, match -> match })

    val materialDesignation: rule =
        seq(usual_whitespace.maybe(), openBracket, data, closeBracket, usual_whitespace.maybe())
            .push(with_string { _, items, _ -> MaterialDesignation(items[0] as String) })

    val otherInfo: rule = seq(colon, data)
        .push(with_string { _, items, _ -> OtherTitleInfo(items[0] as String) })

    val title: rule = seq(data, materialDesignation.maybe(), otherInfo.maybe())
        .push(with_string { _, items, _ ->
            Title(
                items[0] as String,
                items[1] as MaterialDesignation?,
                items[2] as OtherTitleInfo?
            )
        })

    val parallelTitle: rule = seq(equalSign, title)
        .push(with_string { _, items, _ -> ParallelTitle(items[0] as Title) })

    val parallelTitleList: rule = parallelTitle.at_least(1)
        .push(with_string { _, items, _ ->
            ParallelTitleList(items.filterNotNull().map { it as ParallelTitle })
        })

    val sorList: rule = seq(semicolon, data).at_least(1)

    val sor: rule = seq(slash, data, sorList.maybe())
        .push(with_string { _, items, _ ->
            SOR(items.filterNotNull().map { it as String })
        })

    val titleStatement: rule = seq(
        title,
        parallelTitleList.maybe(),
        sor.maybe()
    ).push(with_string { _, items, _ ->
        TitleStatement(
            items[0] as Title,
            items[1] as ParallelTitleList?,
            items[2] as SOR?
        )
    })

    val root: rule = titleStatement

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