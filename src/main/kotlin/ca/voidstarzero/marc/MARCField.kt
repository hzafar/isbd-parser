package ca.voidstarzero.marc

class MARCField(
    val tag: String,
    val indicators: List<Char>,
    val subfields: List<Pair<Char, String>>
) {
    constructor(fieldTag: String, fieldData: String, separator: Char = 0x1f.toChar()) :
            this(fieldTag, emptyList(), fieldData, separator)

    constructor(fieldTag: String, indicators: List<Char>, fieldData: String, separator: Char = 0x1f.toChar()) :
            this(
                tag = fieldTag,
                indicators = indicators,
                subfields = fieldData.split(separator)
                    .filterNot(String::isNullOrEmpty)
                    .map { subfield ->
                    Pair(subfield[0], subfield.substring(1))
                }
            )
}