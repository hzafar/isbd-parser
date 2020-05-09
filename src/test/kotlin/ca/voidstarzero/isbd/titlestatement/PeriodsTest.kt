package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert
import org.junit.Test

class PeriodsTest {

    private val t: TitleStatement =
        TitleStatement()

    @Test
    fun t1() {
        val title = "Moreninha = The little paper doll : from A Prole do Bebe, no. 1 / " +
                "Villa-Lobos ; arr. for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Moreninha"),
                        otherInfo = listOf(OtherInfo("from A Prole do Bebe, no 1")),
                        parallelTitles = listOf(ParallelTitle("The little paper doll"))
                    )
                ),
                sors = listOf(
                    SOR("Villa-Lobos"),
                    SOR("arr for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t2() {
        val title = "Sonate d-moll : fur Altblockflote (Querflote) und Basso continuo = " +
                "Sonata D minor : for treble recorder (flute) and basso continuo, opus III, " +
                "no. 6 / Jean Baptiste (John) Loeillet ; hrsg. von Hugo Ruf."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Sonate d-moll"),
                        otherInfo = listOf(
                            OtherInfo("fur Altblockflote (Querflote) und Basso continuo")
                        ),
                        parallelTitles = listOf(
                            ParallelTitle(
                                title = "Sonata D minor",
                                otherInfo = listOf(
                                    ParallelOtherInfo("for treble recorder (flute) and basso " +
                                            "continuo, opus III, no 6")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(
                    SOR("Jean Baptiste (John) Loeillet"),
                    SOR("hrsg von Hugo Ruf")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t3() {
        val title = "A source book in theatrical history = Sources of theatrical history / by A. M. Nagler."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("A source book in theatrical history"),
                        parallelTitles = listOf(ParallelTitle("Sources of theatrical history"))
                    )
                ),
                sors = listOf(SOR("by A M Nagler"))
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t4() {
        val title = "Perrazo y Perrito = Big Dog and Little Dog / Dav Pilkey ; traducido por Carlos E. Calvo."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Perrazo y Perrito"),
                        parallelTitles = listOf(ParallelTitle("Big Dog and Little Dog"))
                    )
                ),
                sors = listOf(
                    SOR("Dav Pilkey"),
                    SOR("traducido por Carlos E Calvo")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t5() {
        val title = "Das Kantatenwerk Vol. 34 [sound recording] = Complete canatas" +
                " / Johann Sebastian Bach."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Das Kantatenwerk Vol 34 [sound recording]"),
                        parallelTitles = listOf(ParallelTitle("Complete canatas"))
                    )
                ),
                sors = listOf(SOR("Johann Sebastian Bach"))
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t6() {
        val title = "The new churches of Europe = Las neuvas iglesias de Europa" +
                " / [by] G. E. Kidder Smith."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("The new churches of Europe"),
                        parallelTitles = listOf(ParallelTitle("Las neuvas iglesias de Europa"))
                    )
                ),
                sors = listOf(SOR("[by] G E Kidder Smith"))
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t7() {
        val title = "Good night, Mr. Panda = Buenas noches, Sr. Panda" +
                " / Steven Antony ; translated by J.P. Lombana."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Good night, Mr Panda"),
                        parallelTitles = listOf(ParallelTitle("Buenas noches, Sr Panda"))
                    )
                ),
                sors = listOf(
                    SOR("Steven Antony"),
                    SOR("translated by JP Lombana")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t8() {
        val title = "Brown bear, brown bear, what do you see? = Oso pardo, " +
                "oso pardo, ¿qué ves ahí? / Bill Martin, Jr. ; pictures by/illustraciones " +
                "de Eric Carle ; translation by/traducción de Teresa Mlawer."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Brown bear, brown bear, what do you see?"),
                        parallelTitles = listOf(
                            ParallelTitle("Oso pardo, oso pardo, ¿qué ves ahí?")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Bill Martin, Jr"),
                    SOR("pictures by/illustraciones de Eric Carle"),
                    SOR("translation by/traducción de Teresa Mlawer")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t9() {
        val title = "Crosscurrents of modernism : four Latin American pioneers" +
                " : Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta" +
                " = Intercambios del modernismo : cuatro precursores latinoamericanos" +
                " : Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta" +
                " / Valerie Fletcher with essays by Olivier Debroise ... [et al.]" +
                " ; [English translations by James Oles, Margaret Sayers Peden, " +
                "and Eliot Weinberger ; Spanish translations by Carlos Banales and " +
                "Carlos Tripoldi]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Crosscurrents of modernism"),
                        otherInfo = listOf(
                            OtherInfo("four Latin American pioneers"),
                            OtherInfo("Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta")
                        ),
                        parallelTitles = listOf(
                            ParallelTitle(
                                title = "Intercambios del modernismo",
                                otherInfo = listOf(
                                    ParallelOtherInfo("cuatro precursores latinoamericanos"),
                                    ParallelOtherInfo("Diego Rivera, Joaquin Torres-Garcia, " +
                                            "Wifredo Lam, Matta")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(
                    SOR("Valerie Fletcher with essays by Olivier Debroise  [et al]"),
                    SOR(
                        "[English translations by James Oles, Margaret Sayers " +
                                "Peden, and Eliot Weinberger"
                    ),
                    SOR("Spanish translations by Carlos Banales and Carlos Tripoldi]")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t10() {
        val title = "Huasipungo = The villagers : a novel / authorized translation and " +
                "introduction by Bernard M. Dulsey ; foreword by J. Cary Davis."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Huasipungo"),
                        otherInfo = listOf(OtherInfo("a novel")),
                        parallelTitles = listOf(ParallelTitle("The villagers"))
                    )
                ),
                sors = listOf(
                    SOR("authorized translation and introduction by Bernard M Dulsey"),
                    SOR("foreword by J Cary Davis")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t11() {
        val title = "Larvae and pupae of Integripalpia = Lichinki i kukolki podotryada " +
                "tselnoshchupikovych / S. G. Lepneva ; translated from Russian."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Larvae and pupae of Integripalpia"),
                        parallelTitles = listOf(
                            ParallelTitle("Lichinki i kukolki podotryada " +
                                    "tselnoshchupikovych")
                        )
                    )
                ),
                sors = listOf(
                    SOR("S G Lepneva"),
                    SOR("translated from Russian")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t12() {
        val title = "Preparatory trill studies = Estudios preparatorios de trinos" +
                " : for the violin : for developing the touch and strength and surety " +
                "of the fingers : op. 7 / Otakar Sevcik ; edited by Louis Svencenski."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Preparatory trill studies"),
                        otherInfo = listOf(
                            OtherInfo("for the violin"),
                            OtherInfo("for developing the touch and strength " +
                                    "and surety of the fingers"),
                            OtherInfo("op 7")
                        ),
                        parallelTitles = listOf(ParallelTitle("Estudios preparatorios de trinos"))
                    )
                ),
                sors = listOf(
                    SOR("Otakar Sevcik"),
                    SOR("edited by Louis Svencenski")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t13() {
        val title = "Folk songs = Deutsche Volkslieder : for voice and piano, German " +
                "and English texts / [arr. by] Brahms ; [English translation by Regina " +
                "Winternitz]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Folk songs"),
                        otherInfo = listOf(
                            OtherInfo("for voice and piano, German and English texts")
                        ),
                        parallelTitles = listOf(ParallelTitle("Deutsche Volkslieder"))
                    )
                ),
                sors = listOf(
                    SOR("[arr by] Brahms"),
                    SOR("[English translation by Regina Winternitz]")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t14() {
        val title = "Thank you, Mr. Panda = Gracias, Sr. Panda / Steve Antony."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Thank you, Mr Panda"),
                        parallelTitles = listOf(ParallelTitle("Gracias, Sr Panda"))
                    )
                ),
                sors = listOf(SOR("Steve Antony"))
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t15() {
        val title = "La mer [sound recording] / Debussy. Valses nobles et " +
                "sentimentales ; La valse / Ravel. Suite no. 2 from Bacchus et " +
                "Ariane / Roussel."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("La mer [sound recording]")
                    )
                ),
                sors = listOf(SOR("Debussy"))
            ),
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Valses nobles et sentimentales")
                    ),
                    Title(
                        titleProper = TitleProper("La valse")
                    )
                ),
                sors = listOf(SOR("Ravel"))
            ),
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Suite no 2 from Bacchus et Ariane")
                    )
                ),
                sors = listOf(SOR("Roussel"))
            )
        )

        //val result = t.parseHeuristically(title)

        //Assert.assertNotNull(result)
        //Assert.assertEquals(expected, result)
        println(t.parseAll(title))
    }

    @Test
    fun t16() {
        val title = "Beethoven's letters / with explanatory notes by A. C. " +
                "Kalischer ; translated with pref by J. S. Shedlock ; selected " +
                "and edited by A. Eaglefield-Hull."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Beethoven's letters")
                    )
                ),
                sors = listOf(
                    SOR("with explanatory notes by A C Kalischer"),
                    SOR("translated with pref by J S Shedlock"),
                    SOR("selected and edited by A Eaglefield-Hull")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t17() {
        val title = "Rickshaw boy / by Lau Shaw [pseud.] ; Translated from the Chinese " +
                "by Even King [pseud.] ; sketches by Cyrus LeRoy Baldridge."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Rickshaw boy")
                    )
                ),
                sors = listOf(
                    SOR("by Lau Shaw [pseud]"),
                    SOR("Translated from the Chinese by Even King [pseud]"),
                    SOR("sketches by Cyrus LeRoy Baldridge")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t18() {
        val title = "Joyful wisdom / with an introd. by Kurt F. Reinhardt" +
                " ; translated by Thomas Common ; Poetry versions by Paul V. " +
                "Cohn and Maude D. Petre."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Joyful wisdom")
                    )
                ),
                sors = listOf(
                    SOR("with an introd by Kurt F Reinhardt"),
                    SOR("translated by Thomas Common"),
                    SOR("Poetry versions by Paul V Cohn and Maude D Petre")
                )
            )
        )

        val result = t.parse(title)

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }
}