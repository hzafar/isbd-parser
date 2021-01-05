package ca.voidstarzero.marc

fun MARCField.fieldData(): String {
    return this.subfields
        .filter { (c, _) -> c !in listOf('6', '8') }
        .joinToString(separator = " ") { (_, d) -> d }
}

fun MARCField.groupSeriesEntries(): List<List<Pair<Char, String>>> {
    var seriesFields = this.subfields.filter { it.first in listOf('n', 'p') }

    val result = mutableListOf<List<Pair<Char, String>>>()
    while (seriesFields.isNotEmpty()) {
        val group = mutableListOf<Pair<Char, String>>()
        if (seriesFields[0].first == 'n') {
            group.add(seriesFields[0])
            seriesFields = seriesFields.drop(1)
        }

        if (seriesFields.isNotEmpty() && seriesFields[0].first == 'p') {
            group.add(seriesFields[0])
            seriesFields = seriesFields.drop(1)
        }

        result.add(group)
    }

    return result
}