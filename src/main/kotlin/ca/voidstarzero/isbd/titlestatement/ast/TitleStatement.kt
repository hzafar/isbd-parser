package ca.voidstarzero.isbd.titlestatement.ast

data class TitleStatement(
    val titles: List<Title> = listOf(),
    val sors: List<SOR> = listOf(),
    val parallelTitles: List<ParallelTitle> = listOf(),
    val parallelSORs: List<ParallelSOR> = listOf()
)