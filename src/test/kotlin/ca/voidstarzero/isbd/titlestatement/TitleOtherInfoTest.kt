package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.Monograph
import ca.voidstarzero.isbd.titlestatement.ast.OtherInfo
import ca.voidstarzero.isbd.titlestatement.ast.TitleProper
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatement
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TitleOtherInfoTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    /********************************************
     *
     * "Title proper : other title information"
     */

    @Test
    fun t1() {
        val title = "Smokey Joe's Cafe [sound recording] : the songs of Leiber and Stoller."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Smokey Joe's Cafe [sound recording]"),
                        otherInfo = listOf(OtherInfo("the songs of Leiber and Stoller"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t2() {
        val title = "Nilsson [sound recording] : the RCA albums collection."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Nilsson [sound recording]"),
                        otherInfo = listOf(OtherInfo("the RCA albums collection"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}