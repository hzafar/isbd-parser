package ca.voidstarzero.isbd.titlestatement

data class TitleParseContext(
    var currentState: State
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