package ca.voidstarzero.isbd.titlestatement.ast

data class Title(
    val titleProper: TitleProper,
    val otherInfo: List<OtherInfo> = emptyList(),
    val parallelOtherInfo: List<ParallelOtherInfo> = emptyList(),
    val parallelTitles: List<ParallelTitle> = emptyList()
) : Node()