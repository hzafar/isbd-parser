package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.TitleProper
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatementNode

fun likelyTitle(titleProper: TitleProper): Boolean {
    return !titleProper.value[0].isDigit()
}

fun goodParse(parse: List<TitleStatementNode>): Boolean {
    return parse.all { node ->
        node.titles.all { likelyTitle(it.titleProper) }
    }
}