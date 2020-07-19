package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TitleSequenceTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    /*****************************************************
     *
     * "Title 1 ; Title 2 / statement of responsibility"
     */


    /***************************************************************
     *
     * "Title 1 : other title information 1 ; Title 2
     *   : other title information 2 / statement of responsibility"
     */

    /************************************************************
     *
     * "Title 1 = Parallel title 1 ; Title 2 = Parallel title 2
     *   / statement of responsibility"
     */

    @Test
    fun t8() {
        val title = "Die Klaviersonaten [sound recording] = The piano sonatas ; Tänze = Dances" +
                " : complete recording / Franz Schubert."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Die Klaviersonaten [sound recording]"),
                        parallelTitles = listOf(ParallelMonograph("The piano sonatas"))
                    ),
                    Monograph(
                        titleProper = TitleProper("Tänze"),
                        parallelTitles = listOf(ParallelMonograph("Dances")),
                        otherInfo = listOf(OtherInfo("complete recording"))
                    )
                ),
                sors = listOf(SOR("Franz Schubert"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t40() {
        val title = "3 symphonies [sound recording] ; The rock = Der Fels = Le rocher / Serge Rachmaninoff."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("3 symphonies [sound recording]")
                    ),
                    Monograph(
                        titleProper = TitleProper("The rock"),
                        parallelTitles = listOf(
                            ParallelMonograph("Der Fels"),
                            ParallelMonograph("Le rocher")
                        )
                    )
                ),
                sors = listOf(SOR("Serge Rachmaninoff"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}