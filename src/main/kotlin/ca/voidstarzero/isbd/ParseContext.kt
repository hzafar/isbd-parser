package ca.voidstarzero.isbd

data class ParseContext(
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