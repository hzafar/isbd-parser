package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class PunctuationPatternsTest {

    private val t: TitleStatement =
        TitleStatement()

    @Test
    fun p1() {
        val pattern = "Title proper"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Title proper"))
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                otherInfos = listOf(OtherInfo("other title information"))
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                parallelTitles = listOf(ParallelTitle("Parallel title"))
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                otherInfos = listOf(OtherInfo("other title information")),
                parallelTitles = listOf(ParallelTitle("Parallel title"))
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                otherInfos = listOf(OtherInfo("other title information")),
                parallelTitles = listOf(ParallelTitle("Parallel title")),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("parallel other title information")
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                otherInfos = listOf(OtherInfo("other title information")),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("parallel other title information")
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                parallelTitles = listOf(ParallelTitle("Parallel title")),
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                parallelTitles = listOf(
                    ParallelTitle("Parallel title 1"),
                    ParallelTitle("Parallel title 2")
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                otherInfos = listOf(
                    OtherInfo("other title information 1"),
                    OtherInfo("other title information 2")
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
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
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
                sors = listOf(SOR("statement of responsibility")),
                parallelTitles = listOf(ParallelTitle("Parallel title")),
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
    fun p13() {
        val pattern = "Title proper / statement of responsibility" +
                " ; second statement of responsibility" +
                " ; third statement of responsibility"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Title proper")),
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
            TitleStatementNode(
                titles = listOf(Title("Title 1")),
                sors = listOf(SOR("statement of responsibility 1"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Title 2")),
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
            TitleStatementNode(
                titles = listOf(
                    Title("Title 1"),
                    Title("Title 2")
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
            TitleStatementNode(
                titles = listOf(
                    Title("Title 1"),
                    Title("Title 2")
                ),
                otherInfos = listOf(
                    OtherInfo("other title information 1"),
                    OtherInfo("other title information 2")
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
            TitleStatementNode(
                titles = listOf(
                    Title("Title 1"),
                    Title("Title 2")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Parallel title 1"),
                    ParallelTitle("Parallel title 2")
                ),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parse(pattern)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p18() {
        val pattern = "Common title. Dependent title"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title"))
            )
        )

        val result = t.parseAll(pattern)[1]

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p19() {
        val pattern = "Common title. Dependent title designation" +
                ", Dependent title"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title designation, Dependent title"))
            )
        )

        val result = t.parseAll(pattern)[1]

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p20() {
        val pattern = "Common title. Dependent title designation"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title designation"))
            )
        )

        val result = t.parseAll(pattern)[1]

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p21() {
        val pattern = "Common title. Dependent title = Parallel common title" +
                ". Parallel dependent title"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title")),
                parallelTitles = listOf(ParallelTitle("Parallel common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Parallel dependent title"))
            )
        )

        val result = t.parseAll(pattern)[3]

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p22() {
        val pattern = "Common title. Dependent title / statement of responsibility"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title")),
                sors = listOf(SOR("statement of responsibility"))
            )
        )

        val result = t.parseAll(pattern)[1]

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p23() {
        val pattern = "Common title : other title information. Dependent title" +
                " : other title information"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title")),
                otherInfos = listOf(OtherInfo("other title information"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title")),
                otherInfos = listOf(OtherInfo("other title information"))
            )
        )

        val result = t.parseAll(pattern)[1]

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    // FIXME
    fun p24() {
        val pattern = "Common title. Dependent title : other title information" +
                " / statement of responsibility = Parallel common title" +
                ". Parallel dependent title : parallel other title information" +
                " / parallel statement of responsibility"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Dependent title")),
                otherInfos = listOf(OtherInfo("other title information")),
                sors = listOf(SOR("statement of responsibility")),
                parallelSORs = listOf(ParallelSOR("Parallel common title"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Parallel dependent title")),
                otherInfos = listOf(
                    OtherInfo("parallel other title information")
                ),
                sors = listOf(
                    SOR("parallel statement of responsibility")
                )
            )
        )

        val result = t.parseAll(pattern)[3]

        assertNotNull(result)
        assertEquals(expected, result)
    }
}