package ca.voidstarzero.isbd

import norswap.autumn.Autumn
import norswap.autumn.DSL
import norswap.autumn.ParseOptions

class Grammar : DSL() {
    private val openBracket: rule = str("[")
    private val closeBracket: rule = str("]")
    private val equalSign: rule = str("_=_")
    private val colon: rule = str("_:_")
    private val slash: rule = str("_/_")
    private val semicolon: rule = str("_;_")
    private val point: rule = str("._")
    private val comma: rule = str(",_")
    private val char = any
    private val data: rule = char.at_least(1).sep(0, usual_whitespace)


    val materialDesignation: rule =
        seq(usual_whitespace.maybe(), openBracket, data, closeBracket, usual_whitespace.maybe())
    val otherInfo: rule = seq(colon, data)
    val parallelTitle: rule = seq(equalSign, data)
    val sor: rule = seq(slash, data)
    val titleStatement: rule = seq(
        data,
        materialDesignation.maybe(),
        otherInfo.maybe(),
        parallelTitle.maybe(),
        sor.maybe()
    )

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
            .replace(". ", "._")
            .replace(", ", ",_")
    }

    /*
    fun parse(input: String): ISBDParseResult? {
        val titleString = prepare(input)
        val result = Autumn.parse(root, titleString, ParseOptions.get())

        if (result.success) {
            return ISBDParseResult(
                titles = listOf(
                    TitleProper(title = input.substring(0, result.match_size))
                )
            )
        }

        return null
    }
     */

    fun parse(input: String): String {
        val titleString = prepare(input)
        val result = Autumn.parse(root, titleString, ParseOptions.get())

        println(input.substring(0, result.match_size))
        return result.toString()
    }
}