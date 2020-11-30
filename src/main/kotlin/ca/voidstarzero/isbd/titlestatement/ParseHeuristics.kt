package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.Monograph
import ca.voidstarzero.isbd.titlestatement.ast.Series
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatement

fun likelyTitle(title: String): Boolean {
    return !title[0].isDigit()
            && !title.startsWith(')')
}

fun goodParse(parse: List<TitleStatement>): Boolean {
    return parse.all { node ->
        node.titles.mapNotNull { when(it) {
            is Monograph -> it.titleProper.value
            is Series -> it.seriesTitle.title
            else -> null
        } }
            .all { likelyTitle(it) }
    }
}