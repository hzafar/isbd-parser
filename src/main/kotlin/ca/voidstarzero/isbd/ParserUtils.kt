package ca.voidstarzero.isbd

fun cleanInput(input: String): String {
    val REPLACE = 0xfffd.toChar()
    return input
        .replace(" = ", "$REPLACE=$REPLACE")
        .replace(" : ", "$REPLACE:$REPLACE")
        .replace(" / ", "$REPLACE/$REPLACE")
        .replace(" ; ", "$REPLACE;$REPLACE")
        .removeSuffix(".")
        .trim()
}
fun prepare(input: String): List<String> {
    return droppedPeriods(cleanInput(input))
}

fun findPeriods(input: String): List<Int> {
    return input.mapIndexedNotNull { i, c ->
        when (c) {
            '.' -> i
            else -> null
        }
    }
}

fun periodCombinations(positions: List<Int>): List<List<Int>> {
    return when (positions) {
        emptyList<Int>() -> listOf(emptyList())
        else -> {
            val rest = periodCombinations(
                positions.subList(
                    1,
                    positions.size
                )
            )
            rest.map { it.plus(positions[0]) }
                .plus(rest)
        }
    }
}

fun droppedPeriods(input: String): List<String> {
    return periodCombinations(
        findPeriods(
            input
        )
    ).map { combination ->
        input.filterIndexed { i, _ -> i !in combination }
    }
}
