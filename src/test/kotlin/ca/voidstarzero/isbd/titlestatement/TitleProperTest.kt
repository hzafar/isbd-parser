package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.Monograph
import ca.voidstarzero.isbd.titlestatement.ast.TitleProper
import ca.voidstarzero.isbd.titlestatement.ast.TitleStatement
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TitleProperTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    /******************
     *
     * "Title proper"
     */

    @Test
    fun t1() {
        val title = "A dictionary of Mocǎ (southwestern Ethiopia)"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("A dictionary of Mocǎ (southwestern Ethiopia)")
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}