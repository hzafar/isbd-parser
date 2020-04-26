package ca.voidstarzero.isbd

import norswap.autumn.*

class Grammar : DSL() {
    private val openBracket: rule = str("_[")
    private val closeBracket: rule = str("]_")
    private val equalSign: rule = str("_=_")
    private val colon: rule = str("_:_")
    private val slash: rule = str("_/_")
    private val semicolon: rule = str("_;_")
    private val point: rule = str("._")
    private val comma: rule = str(",_")
    private val data: rule = alphanum.at_least(1) // FIXME

    val materialDesignation: rule = seq(openBracket, data, closeBracket)
    val title: rule = seq(data, materialDesignation.maybe(), colon)

    val root: rule = title

    init {
        ws = usual_whitespace
        make_rule_names()
    }

    fun parse(input: String): ParseResult {
        return Autumn.parse(root, input, ParseOptions.get())
    }
}