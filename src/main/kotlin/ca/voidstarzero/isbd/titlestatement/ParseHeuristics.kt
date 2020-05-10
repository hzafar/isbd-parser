package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.TitleProper
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatement

fun likelyTitle(titleProper: TitleProper): Boolean {
    return !titleProper.value[0].isDigit()
            && !titleProper.value.startsWith(')')
}

fun goodParse(parse: List<TitleStatement>): Boolean {
    return parse.all { node ->
        node.titles.all { likelyTitle(it.titleProper) }
    }
}