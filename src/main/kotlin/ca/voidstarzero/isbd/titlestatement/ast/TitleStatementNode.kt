package ca.voidstarzero.isbd.titlestatement.ast

data class TitleStatementNode(
    val titles: List<Title> = listOf(),
    val sors: List<SOR> = listOf(),
    val parallelSORs: List<ParallelSOR> = listOf()
)