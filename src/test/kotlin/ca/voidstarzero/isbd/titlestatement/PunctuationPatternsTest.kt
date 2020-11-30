package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class PunctuationPatternsTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    @Test
    fun p1() {
        val pattern = "Title proper"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper")
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p2() {
        val pattern = "Title proper : other title information"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        otherInfo = listOf(OtherInfo("other title information"))
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p3() {
        val pattern = "Title proper = Parallel title"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        parallelTitles = listOf(ParallelMonograph("Parallel title"))
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p4() {
        val pattern = "Title proper = Parallel title : other title information"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        otherInfo = listOf(OtherInfo("other title information")),
                        parallelTitles = listOf(ParallelMonograph("Parallel title"))
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p5() {
        val pattern = "Title proper : other title information" +
                " = Parallel title : parallel other title information"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        otherInfo = listOf(OtherInfo("other title information")),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "Parallel title",
                                otherInfo = listOf(
                                    ParallelOtherInfo("parallel other title information")
                                )
                            )
                        )
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p6() {
        val pattern = "Title proper : other title information" +
                " = parallel other title information"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        otherInfo = listOf(OtherInfo("other title information")),
                        parallelOtherInfo = listOf(
                            ParallelOtherInfo("parallel other title information")
                        )
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p7() {
        val pattern = "Title proper / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper")
                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p8() {
        val pattern = "Title proper = Parallel title" +
                " / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        parallelTitles = listOf(ParallelMonograph("Parallel title"))
                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p9() {
        val pattern = "Title proper = Parallel title 1 = Parallel title 2" +
                " / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        parallelTitles = listOf(
                            ParallelMonograph("Parallel title 1"),
                            ParallelMonograph("Parallel title 2")
                        )
                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p10() {
        val pattern = "Title proper : other title information 1" +
                " : other title information 2 / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper"),
                        otherInfo = listOf(
                            OtherInfo("other title information 1"),
                            OtherInfo("other title information 2")
                        )
                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p11() {
        val pattern = "Title proper / statement of responsibility" +
                " = parallel statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper")
                    )
                ),
                sors = listOf(SOR("statement of responsibility")),
                parallelSORs = listOf(
                    ParallelSOR("parallel statement of responsibility")
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p12() {
        val pattern = "Title proper / statement of responsibility" +
                " = Parallel title / parallel statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper")
                    )
                ),
                sors = listOf(SOR("statement of responsibility")),
                parallelTitles = listOf(
                    ParallelMonograph(
                        title = "Parallel title",
                        sors = listOf(
                            ParallelSOR("parallel statement of responsibility")
                        )
                    )
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p13() {
        val pattern = "Title proper / statement of responsibility" +
                " ; second statement of responsibility" +
                " ; third statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title proper")
                    )
                ),
                sors = listOf(
                    SOR("statement of responsibility"),
                    SOR("second statement of responsibility"),
                    SOR("third statement of responsibility")
                )
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p14() {
        val pattern = "Title 1 / statement of responsibility 1. Title 2" +
                " / statement of responsibility 2"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title 1")
                    )
                ),
                sors = listOf(SOR("statement of responsibility 1"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title 2")
                    )
                ),
                sors = listOf(SOR("statement of responsibility 2"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p15() {
        val pattern = "Title 1 ; Title 2 / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title 1")
                    ),
                    Monograph(
                        titleProper = TitleProper("Title 2")
                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p16() {
        val pattern = "Title 1 : other title information 1 ; Title 2" +
                " : other title information 2 / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title 1"),
                        otherInfo = listOf(OtherInfo("other title information 1"))
                    ),
                    Monograph(
                        titleProper = TitleProper("Title 2"),
                        otherInfo = listOf(OtherInfo("other title information 2"))

                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p17() {
        val pattern = "Title 1 = Parallel title 1 ; Title 2 = Parallel title 2" +
                " / statement of responsibility"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Title 1"),
                        parallelTitles = listOf(ParallelMonograph("Parallel title 1"))
                    ),
                    Monograph(
                        titleProper = TitleProper("Title 2"),
                        parallelTitles = listOf(ParallelMonograph("Parallel title 2"))
                    )
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p18() {
        val pattern = "Common title. Dependent title"
        val marc = MARCField("245", "|aCommon title.|pDependent title", '|')

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(title = "Common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Dependent title")
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p19() {
        val pattern = "Common title. Dependent title designation, Dependent title"
        val marc = MARCField(
            "245", "|aCommon title.|nDependent title designation," +
                    "|pDependent title", '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(title = "Common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Dependent title"),
                                designation = SeriesEntryDesignation("Dependent title designation")
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p20() {
        val pattern = "Common title. Dependent title designation"
        val marc = MARCField("245", "|aCommon title.|nDependent title designation", '|')

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(title = "Common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                designation = SeriesEntryDesignation("Dependent title designation")
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p21() {
        val pattern = "Common title. Dependent title = Parallel common title" +
                ". Parallel dependent title"
        val marc = MARCField("245", "|aCommon title.|pDependent title =|bParallel " +
                "common title. Parallel dependent title")

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(title = "Common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Dependent title")
                            )
                        )
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeries(
                        seriesTitle = SeriesTitle(title = "Parallel common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Parallel dependent title")
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p22() {
        val pattern = "Common title. Dependent title / statement of responsibility"
        val marc = MARCField("245", "|aCommon title.|pDependent title /" +
                "|cstatement of responsibility")

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(title = "Common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Dependent title"),
                                sors = listOf(
                                    SOR("statement of responsibility")
                                )
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p23() {
        val pattern = "Common title : other title information 1. Dependent title" +
                " : other title information 2"
        val marc = MARCField("245", "|aCommon title :|bother title information 1." +
                "|pDependent title : other title information 2")

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(
                            title = "Common title",
                            otherInfo = listOf(
                                SeriesOtherInfo("other title information 1")
                            )
                        ),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Dependent title"),
                                otherInfo = listOf(
                                    SeriesEntryOtherInfo("other title information 2")
                                )
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun p24() {
        val pattern = "Common title. Dependent title : other title information" +
                " / statement of responsibility = Parallel common title" +
                ". Parallel dependent title : parallel other title information" +
                " / parallel statement of responsibility"
        val marc = MARCField("245", "|aCommon title.|pDependent title :" +
                "|bother title information /|cstatement of responsibility = Parallel common " +
                "title. Parallel dependent title : parallel other title information" +
                " / parallel statement of responsibility")

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Series(
                        seriesTitle = SeriesTitle(title = "Common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Dependent title"),
                                otherInfo = listOf(
                                    SeriesEntryOtherInfo("other title information")
                                ),
                                sors = listOf(
                                    SOR("statement of responsibility")
                                )
                            )
                        )
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeries(
                        seriesTitle = SeriesTitle(title = "Parallel common title"),
                        seriesEntry = listOf(
                            SeriesEntry(
                                title = SeriesEntryTitle("Parallel dependent title"),
                                otherInfo = listOf(
                                    SeriesEntryOtherInfo("parallel other title information")
                                ),
                                sors = listOf(
                                    SOR("parallel statement of responsibility")
                                )
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(pattern, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}