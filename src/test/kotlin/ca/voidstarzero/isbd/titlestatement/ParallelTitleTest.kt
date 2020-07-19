package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ParallelTitleTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    /***********************************
     *
     * "Title proper = Parallel title"
     */

    @Test
    fun t1() {
        val title = "Hiberniae delineatio = atlas of Ireland."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Hiberniae delineatio"),
                        parallelTitles = listOf(ParallelMonograph("atlas of Ireland"))
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
        val title = "Le Quai Des Brumes = Port of Shadows"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Le Quai Des Brumes"),
                        parallelTitles = listOf(ParallelMonograph("Port of Shadows"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t14() {
        val title = "Macuilli tlachtli = Cinco deportes mexicanos = Five Mexican sports" +
                " = Cinq sports mexicains / Raziel Garcia Arroyo."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Macuilli tlachtli"),
                        parallelTitles = listOf(
                            ParallelMonograph("Cinco deportes mexicanos"),
                            ParallelMonograph("Five Mexican sports"),
                            ParallelMonograph("Cinq sports mexicains")
                        )
                    )
                ),
                sors = listOf(SOR("Raziel Garcia Arroyo"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /*************************************************************
     *
     * "Title proper = Parallel title : other title information"
     */

    @Test
    fun t4() {
        val title = "To life [sound recording] = Le chaim! : Jewish party."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("To life [sound recording]"),
                        otherInfo = listOf(OtherInfo("Jewish party")),
                        parallelTitles = listOf(ParallelMonograph("Le chaim!"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t5() {
        val title = "Le rossignol = The nightingale = Die Nachtigall" +
                " : conte lyrique en trois actes"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Le rossignol"),
                        otherInfo = listOf(OtherInfo("conte lyrique en trois actes")),
                        parallelTitles = listOf(
                            ParallelMonograph("The nightingale"),
                            ParallelMonograph("Die Nachtigall")
                        )
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t6() {
        val title = "Mercadet = The Napoleon of finance : a comedy in three acts / " +
                "by Honore de Balzac ; translated by Robert Cornthwaite."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Mercadet"),
                        otherInfo = listOf(OtherInfo("a comedy in three acts")),
                        parallelTitles = listOf(ParallelMonograph("The Napoleon of finance"))
                    )
                ),
                sors = listOf(
                    SOR("by Honore de Balzac"),
                    SOR("translated by Robert Cornthwaite")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t27() {
        val title = "Animals = Animales : English-Spanish / [designed by Hakan Şan Borteçin]."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Animals"),
                        otherInfo = listOf(OtherInfo("English-Spanish")),
                        parallelTitles = listOf(ParallelMonograph("Animales"))
                    )
                ),
                sors = listOf(SOR("[designed by Hakan Şan Borteçin]"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /**********************************************************
     *
     * "Title proper : other title information
     *   = Parallel title : parallel other title information"
     */

    @Test
    fun t7() {
        val title = "Chine [sound recording] : le soleil et la lune = China : the sun and the moon."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Chine [sound recording]"),
                        otherInfo = listOf(OtherInfo("le soleil et la lune")),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "China",
                                otherInfo = listOf(
                                    ParallelOtherInfo("the sun and the moon")
                                )
                            )
                        )
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t10() {
        val title = "The Mexican league : comprehensive player statistics, 1937-2001 = La liga " +
                "Mexicana : estadísticas comprensivas de los jugadores, 1937-2001 / Pedro Treto Cisneros."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The Mexican league"),
                        otherInfo = listOf(OtherInfo("comprehensive player statistics, 1937-2001")),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "La liga Mexicana",
                                otherInfo = listOf(
                                    ParallelOtherInfo("estadísticas comprensivas de los jugadores, 1937-2001")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(SOR("Pedro Treto Cisneros"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t26() {
        val title = "Arc-en-ciel : le plus beau poisson des océans = The rainbow fish" +
                " : the most beautiful fish in the ocean / Marcus Pfister" +
                " ; [English translation by Rosemary Lanning]"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Arc-en-ciel"),
                        otherInfo = listOf(OtherInfo("le plus beau poisson des océans")),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "The rainbow fish",
                                otherInfo = listOf(
                                    ParallelOtherInfo("the most beautiful fish in the ocean")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(
                    SOR("Marcus Pfister"),
                    SOR("[English translation by Rosemary Lanning]")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t11() {
        val title = "That's not fair! : Emma Tenayuca's struggle for justice = No es justo! : " +
                "la lucha de Emma Tenayuca por la justicia / written by Carmen Tafolla & Sharyll " +
                "Teneyuca ; illustrated by Terry Ybáñez ; Spanish translation by Carmen Tafolla."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("That's not fair!"),
                        otherInfo = listOf(
                            OtherInfo("Emma Tenayuca's struggle for justice")
                        ),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "No es justo!",
                                otherInfo = listOf(
                                    ParallelOtherInfo("la lucha de Emma Tenayuca por la justicia")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(
                    SOR("written by Carmen Tafolla & Sharyll Teneyuca"),
                    SOR("illustrated by Terry Ybáñez"),
                    SOR("Spanish translation by Carmen Tafolla")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /**********************************************************
     *
     * "Title proper : other title information
     *   = parallel other title information"
     */

    @Test
    fun t8() {
        val title = "Astérix : calendar = calendrier = Kalender = calendario"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Astérix"),
                        otherInfo = listOf(OtherInfo("calendar")),
                        parallelOtherInfo = listOf(
                            ParallelOtherInfo("calendrier"),
                            ParallelOtherInfo("Kalender"),
                            ParallelOtherInfo("calendario")
                        )
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}