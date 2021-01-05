package ca.voidstarzero.isbd

import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import ca.voidstarzero.marc.fieldData

fun cleanInput(input: String): String {
    val REPLACE = 0xfffd.toChar()
    return input
        .replace(" = ", "$REPLACE=$REPLACE")
        .replace(" : ", "$REPLACE:$REPLACE")
        .replace(" / ", "$REPLACE/$REPLACE")
        .replace(" ; ", "$REPLACE;$REPLACE")
        .replace("...", "___")
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

fun usesISBD(input: MARCField): Boolean {
    if (input.subfields.any { it.first == 'c' }
        && !input.fieldData().contains(" / ")) {
        return false;
    }

    if (input.subfields.any { it.first == 'b' }
        && !input.fieldData().contains(" : | = ".toRegex())) {
        return false;
    }

    return true;
}

fun simpleParse(input: MARCField): TitleStatement {
    return TitleStatement(
        titles = listOf(
            Monograph(
                titleProper = TitleProper(input.subfields.firstOrNull{ it.first == 'a' }?.second ?: ""),
                otherInfo = input.subfields.filter { it.first == 'b' }.map { OtherInfo(it.second) }
            )
        ),
        sors = input.subfields.filter { it.first == 'c' }.map { SOR(it.second) }
    )
}
