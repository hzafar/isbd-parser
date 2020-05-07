package ca.voidstarzero.isbd

data class ParseContext(
    var inState: State,
    var parallel: Boolean
) {
    constructor() : this(
        State.START,
        false
    )

    enum class State {
        START,
        OTHER_INFO,
        SOR
    }
}