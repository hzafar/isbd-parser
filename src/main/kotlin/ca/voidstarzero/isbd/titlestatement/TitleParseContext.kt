package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.marc.MARCField

data class TitleParseContext(
    var currentState: State,
    var marcData: MARCField? = null,
    var marcDataSeriesEntryGroup: Int = 0,
    var parsedDesignation: Boolean = false,
    var parallelInfo: Boolean = false
) {
    constructor() : this(
        State.START
    )

    enum class State {
        START,
        OTHER_INFO,
        SOR
    }
}