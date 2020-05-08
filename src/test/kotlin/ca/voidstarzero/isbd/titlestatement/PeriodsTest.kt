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
                titles = listOf(Title("Moreninha")),
                otherInfos = listOf(OtherInfo("from A Prole do Bebe, no 1")),
                sors = listOf(
                    SOR("Villa-Lobos"),
                    SOR("arr for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe")
                ),
                parallelTitles = listOf(ParallelTitle("The little paper doll"))
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
                titles = listOf(Title("Sonate d-moll")),
                otherInfos = listOf(OtherInfo("fur Altblockflote (Querflote) und Basso continuo")),
                sors = listOf(
                    SOR("Jean Baptiste (John) Loeillet"),
                    SOR("hrsg von Hugo Ruf")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Sonata D minor")
                ),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("for treble recorder (flute) and basso " +
                            "continuo, opus III, no 6")
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
                titles = listOf(Title("A source book in theatrical history")),
                sors = listOf(SOR("by A M Nagler")),
                parallelTitles = listOf(ParallelTitle("Sources of theatrical history"))
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
                titles = listOf(Title("Perrazo y Perrito")),
                sors = listOf(
                    SOR("Dav Pilkey"),
                    SOR("traducido por Carlos E Calvo")
                ),
                parallelTitles = listOf(ParallelTitle("Big Dog and Little Dog"))
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
                titles = listOf(Title("Das Kantatenwerk Vol 34 [sound recording]")),
                sors = listOf(SOR("Johann Sebastian Bach")),
                parallelTitles = listOf(ParallelTitle("Complete canatas"))
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
                titles = listOf(Title("The new churches of Europe")),
                sors = listOf(SOR("[by] G E Kidder Smith")),
                parallelTitles = listOf(ParallelTitle("Las neuvas iglesias de Europa"))
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
                titles = listOf(Title("Good night, Mr Panda")),
                sors = listOf(
                    SOR("Steven Antony"),
                    SOR("translated by JP Lombana")
                ),
                parallelTitles = listOf(ParallelTitle("Buenas noches, Sr Panda"))
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
                titles = listOf(Title("Brown bear, brown bear, what do you see?")),
                sors = listOf(
                    SOR("Bill Martin, Jr"),
                    SOR("pictures by/illustraciones de Eric Carle"),
                    SOR("translation by/traducción de Teresa Mlawer")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Oso pardo, oso pardo, ¿qué ves ahí?")
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
                titles = listOf(Title("Crosscurrents of modernism")),
                otherInfos = listOf(
                    OtherInfo("four Latin American pioneers"),
                    OtherInfo("Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta")
                ),
                sors = listOf(
                    SOR("Valerie Fletcher with essays by Olivier Debroise  [et al]"),
                    SOR(
                        "[English translations by James Oles, Margaret Sayers " +
                                "Peden, and Eliot Weinberger"
                    ),
                    SOR("Spanish translations by Carlos Banales and Carlos Tripoldi]")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Intercambios del modernismo")
                ),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("cuatro precursores latinoamericanos"),
                    ParallelOtherInfo("Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta")
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
                titles = listOf(Title("Huasipungo")),
                otherInfos = listOf(OtherInfo("a novel")),
                sors = listOf(
                    SOR("authorized translation and introduction by Bernard M Dulsey"),
                    SOR("foreword by J Cary Davis")
                ),
                parallelTitles = listOf(ParallelTitle("The villagers"))
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
                titles = listOf(Title("Larvae and pupae of Integripalpia")),
                sors = listOf(
                    SOR("S G Lepneva"),
                    SOR("translated from Russian")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Lichinki i kukolki podotryada " +
                            "tselnoshchupikovych")
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
                titles = listOf(Title("Preparatory trill studies")),
                otherInfos = listOf(
                    OtherInfo("for the violin"),
                    OtherInfo("for developing the touch and strength " +
                            "and surety of the fingers"),
                    OtherInfo("op 7")
                ),
                sors = listOf(
                    SOR("Otakar Sevcik"),
                    SOR("edited by Louis Svencenski")
                ),
                parallelTitles = listOf(ParallelTitle("Estudios preparatorios de trinos"))
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
                titles = listOf(Title("Folk songs")),
                otherInfos = listOf(
                    OtherInfo("for voice and piano, German and English texts")
                ),
                sors = listOf(
                    SOR("[arr by] Brahms"),
                    SOR("[English translation by Regina Winternitz]")
                ),
                parallelTitles = listOf(ParallelTitle("Deutsche Volkslieder"))
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
                titles = listOf(Title("Thank you, Mr Panda")),
                sors = listOf(SOR("Steve Antony")),
                parallelTitles = listOf(ParallelTitle("Gracias, Sr Panda"))
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
                titles = listOf(Title("La mer [sound recording]")),
                sors = listOf(SOR("Debussy"))
            ),
            TitleStatementNode(
                titles = listOf(
                    Title("Valses nobles et sentimentales"),
                    Title("La valse")
                ),
                sors = listOf(SOR("Ravel"))
            ),
            TitleStatementNode(
                titles = listOf(Title("Suite no 2 from Bacchus et Ariane")),
                sors = listOf(SOR("Roussel"))
            )
        )

        val result = t.parseAll(title)[1]

        Assert.assertNotNull(result)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun t16() {
        val title = "Beethoven's letters / with explanatory notes by A. C. " +
                "Kalischer ; translated with pref by J. S. Shedlock ; selected " +
                "and edited by A. Eaglefield-Hull."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Beethoven's letters")),
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
                titles = listOf(Title("Rickshaw boy")),
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
                titles = listOf(Title("Joyful wisdom")),
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