package ca.voidstarzero.isbd

data class ISBDParseResult(
    val titles: List<TitleInfo>,
    val statementsOfResp: List<StatementOfResponsibility> = emptyList()
)

sealed class TitleInfo(
    val title: String,
    val otherInfo: List<String>,
    val parallelTitles: List<TitleInfo>
) {
    override fun toString(): String {
        return "Title: $title, " +
                "Other Info:${otherInfo.joinToString()}, " +
                "Parallel Titles: [${parallelTitles.joinToString()}]"
    }
}

class TitleProper(
    title: String,
    otherInfo: List<String> = emptyList(),
    parallelTitles: List<TitleInfo> = emptyList()
) : TitleInfo(title, otherInfo, parallelTitles)

class CommonTitle(
    title: String
) : TitleInfo(title, otherInfo = emptyList(), parallelTitles = emptyList())

class DependentTitle(
    title: String,
    val designation: String?
) : TitleInfo(title, otherInfo = emptyList(), parallelTitles = emptyList())

data class StatementOfResponsibility(
    val statement: String,
    val parallelStatement: String? = null
)