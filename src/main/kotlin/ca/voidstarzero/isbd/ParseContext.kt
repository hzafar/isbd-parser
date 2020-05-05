package ca.voidstarzero.isbd

data class ParseContext(
    var parsedTitleProper: Boolean,
    var parsedOtherInfo: Boolean,
    var parsedParallelTitle: Boolean
) {
    constructor(): this(false, false, false)
}