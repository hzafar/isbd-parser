package ca.voidstarzero.isbd.titlestatement.ast

/**
 * A class holding a single, full title statement.
 *
 * @property titles a list of [Title]s contained in the title statement.
 * @property sors a list of [SOR]s for the titles in this title statement.
 * @property parallelTitles a list of [ParallelTitle]s for the titles in this title statement.
 * @property parallelSORs a list of [ParallelSOR]s for the titles in this title statement.
 */
data class TitleStatement(
    val titles: List<Title> = listOf(),
    val sors: List<SOR> = listOf(),
    val parallelTitles: List<ParallelTitle> = listOf(),
    val parallelSORs: List<ParallelSOR> = listOf()
)