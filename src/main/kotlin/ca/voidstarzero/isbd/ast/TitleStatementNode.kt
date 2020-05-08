package ca.voidstarzero.isbd.ast

data class TitleStatementNode(
    val titles: List<Title> = listOf(),
    val otherInfos: List<OtherInfo> = listOf(),
    val sors: List<SOR> = listOf(),
    val parallelTitles: List<ParallelTitle> = listOf(),
    val parallelOtherInfos: List<ParallelOtherInfo> = listOf(),
    val parallelSORs: List<ParallelSOR> = listOf()
)