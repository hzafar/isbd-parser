package ca.voidstarzero.isbd

class ISBDParsedResult(
    val titles: List<TitleInfo>,
    val parallelTitles: List<TitleInfo> = emptyList()
    /*
    val titles: List<TitleInfo>,
    val titleProper: String,
    val otherTitleInfo: String,
    val parallelTitle: String,
    val parallelOtherTitleInfo: String,
    val statementOfResp: String,
    val parallelStatementOfResp: String,
    val moreStatementsOfResp: List<String>,
    val commonTitle: String,
    val dependentTitle: String,
    val dependentTitleDesignation: String,
    val parallelCommonTitle: String,
    val parallelDependentTitle: String
     */
)

sealed class TitleInfo(
    val title: String,
    val otherInfo: List<String>,
    val statementsOfResp: List<String>
)

class TitleProper(
    title: String,
    otherInfo: List<String> = emptyList(),
    statementsOfResp: List<String> = emptyList()
) : TitleInfo(title, otherInfo, statementsOfResp)

class CommonTitle(
    title: String
) : TitleInfo(title, otherInfo = emptyList(), statementsOfResp = emptyList())

class DependentTitle(
    title: String,
    val designation: String?
) : TitleInfo(title, otherInfo = emptyList(), statementsOfResp = emptyList())