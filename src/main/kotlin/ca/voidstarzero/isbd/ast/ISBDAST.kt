package ca.voidstarzero.isbd.ast

data class MaterialDesignation(val value: String)

data class OtherTitleInfo(val value: String)

data class Title(
    val value: String,
    val designation: MaterialDesignation? = null,
    val otherInfo: OtherTitleInfo? = null
)

data class ParallelTitle(val value: Title)

data class ParallelTitleList(val value: List<ParallelTitle>)

data class SOR(val value: List<String>)

data class TitleStatement(
    val title: Title,
    val parallelTitles: ParallelTitleList? = null,
    val sor: SOR? = null
)