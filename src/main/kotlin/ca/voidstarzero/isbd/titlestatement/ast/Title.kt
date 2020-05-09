package ca.voidstarzero.isbd.titlestatement.ast

data class Title(
    val titleProper: TitleProper,
    val otherInfos: List<OtherInfo> = emptyList(),
    val parallelOtherInfos: List<ParallelOtherInfo> = emptyList(),
    val parallelTitles: List<ParallelTitle> = emptyList()
) : Node()