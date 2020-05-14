package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert.*
import org.junit.Test

class PeriodsTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    @Test
    fun t1() {
        val title = "Moreninha = The little paper doll : from A Prole do Bebe, no. 1 / " +
                "Villa-Lobos ; arr. for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Moreninha"),
                        otherInfo = listOf(OtherInfo("from A Prole do Bebe, no 1")),
                        parallelTitles = listOf(ParallelMonograph("The little paper doll"))
                    )
                ),
                sors = listOf(
                    SOR("Villa-Lobos"),
                    SOR("arr for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe")
                )
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t2() {
        val title = "Sonate d-moll : fur Altblockflote (Querflote) und Basso continuo = " +
                "Sonata D minor : for treble recorder (flute) and basso continuo, opus III, " +
                "no. 6 / Jean Baptiste (John) Loeillet ; hrsg. von Hugo Ruf."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Sonate d-moll"),
                        otherInfo = listOf(
                            OtherInfo("fur Altblockflote (Querflote) und Basso continuo")
                        ),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "Sonata D minor",
                                otherInfo = listOf(
                                    ParallelOtherInfo(
                                        "for treble recorder (flute) and basso " +
                                                "continuo, opus III, no 6"
                                    )
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t3() {
        val title = "A source book in theatrical history = Sources of theatrical history / by A. M. Nagler."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("A source book in theatrical history"),
                        parallelTitles = listOf(ParallelMonograph("Sources of theatrical history"))
                    )
                ),
                sors = listOf(SOR("by A M Nagler"))
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t4() {
        val title = "Perrazo y Perrito = Big Dog and Little Dog / Dav Pilkey ; traducido por Carlos E. Calvo."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Perrazo y Perrito"),
                        parallelTitles = listOf(ParallelMonograph("Big Dog and Little Dog"))
                    )
                ),
                sors = listOf(
                    SOR("Dav Pilkey"),
                    SOR("traducido por Carlos E Calvo")
                )
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t5() {
        val title = "Das Kantatenwerk Vol. 34 [sound recording] = Complete canatas" +
                " / Johann Sebastian Bach."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Das Kantatenwerk Vol 34 [sound recording]"),
                        parallelTitles = listOf(ParallelMonograph("Complete canatas"))
                    )
                ),
                sors = listOf(SOR("Johann Sebastian Bach"))
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t6() {
        val title = "The new churches of Europe = Las neuvas iglesias de Europa" +
                " / [by] G. E. Kidder Smith."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The new churches of Europe"),
                        parallelTitles = listOf(ParallelMonograph("Las neuvas iglesias de Europa"))
                    )
                ),
                sors = listOf(SOR("[by] G E Kidder Smith"))
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t7() {
        val title = "Good night, Mr. Panda = Buenas noches, Sr. Panda" +
                " / Steven Antony ; translated by J.P. Lombana."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Good night, Mr Panda"),
                        parallelTitles = listOf(ParallelMonograph("Buenas noches, Sr Panda"))
                    )
                ),
                sors = listOf(
                    SOR("Steven Antony"),
                    SOR("translated by JP Lombana")
                )
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t8() {
        val title = "Brown bear, brown bear, what do you see? = Oso pardo, " +
                "oso pardo, ¿qué ves ahí? / Bill Martin, Jr. ; pictures by/illustraciones " +
                "de Eric Carle ; translation by/traducción de Teresa Mlawer."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Brown bear, brown bear, what do you see?"),
                        parallelTitles = listOf(
                            ParallelMonograph("Oso pardo, oso pardo, ¿qué ves ahí?")
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Crosscurrents of modernism"),
                        otherInfo = listOf(
                            OtherInfo("four Latin American pioneers"),
                            OtherInfo("Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta")
                        ),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "Intercambios del modernismo",
                                otherInfo = listOf(
                                    ParallelOtherInfo("cuatro precursores latinoamericanos"),
                                    ParallelOtherInfo(
                                        "Diego Rivera, Joaquin Torres-Garcia, " +
                                                "Wifredo Lam, Matta"
                                    )
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t10() {
        val title = "Huasipungo = The villagers : a novel / authorized translation and " +
                "introduction by Bernard M. Dulsey ; foreword by J. Cary Davis."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Huasipungo"),
                        otherInfo = listOf(OtherInfo("a novel")),
                        parallelTitles = listOf(ParallelMonograph("The villagers"))
                    )
                ),
                sors = listOf(
                    SOR("authorized translation and introduction by Bernard M Dulsey"),
                    SOR("foreword by J Cary Davis")
                )
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t11() {
        val title = "Larvae and pupae of Integripalpia = Lichinki i kukolki podotryada " +
                "tselnoshchupikovych / S. G. Lepneva ; translated from Russian."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Larvae and pupae of Integripalpia"),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                "Lichinki i kukolki podotryada " +
                                        "tselnoshchupikovych"
                            )
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t12() {
        val title = "Preparatory trill studies = Estudios preparatorios de trinos" +
                " : for the violin : for developing the touch and strength and surety " +
                "of the fingers : op. 7 / Otakar Sevcik ; edited by Louis Svencenski."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Preparatory trill studies"),
                        otherInfo = listOf(
                            OtherInfo("for the violin"),
                            OtherInfo(
                                "for developing the touch and strength " +
                                        "and surety of the fingers"
                            ),
                            OtherInfo("op 7")
                        ),
                        parallelTitles = listOf(ParallelMonograph("Estudios preparatorios de trinos"))
                    )
                ),
                sors = listOf(
                    SOR("Otakar Sevcik"),
                    SOR("edited by Louis Svencenski")
                )
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t13() {
        val title = "Folk songs = Deutsche Volkslieder : for voice and piano, German " +
                "and English texts / [arr. by] Brahms ; [English translation by Regina " +
                "Winternitz]."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Folk songs"),
                        otherInfo = listOf(
                            OtherInfo("for voice and piano, German and English texts")
                        ),
                        parallelTitles = listOf(ParallelMonograph("Deutsche Volkslieder"))
                    )
                ),
                sors = listOf(
                    SOR("[arr by] Brahms"),
                    SOR("[English translation by Regina Winternitz]")
                )
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t14() {
        val title = "Thank you, Mr. Panda = Gracias, Sr. Panda / Steve Antony."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Thank you, Mr Panda"),
                        parallelTitles = listOf(ParallelMonograph("Gracias, Sr Panda"))
                    )
                ),
                sors = listOf(SOR("Steve Antony"))
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t15() {
        val title = "La mer [sound recording] / Debussy. Valses nobles et " +
                "sentimentales ; La valse / Ravel. Suite no. 2 from Bacchus et " +
                "Ariane / Roussel."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("La mer [sound recording]")
                    )
                ),
                sors = listOf(SOR("Debussy"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Valses nobles et sentimentales")
                    ),
                    Monograph(
                        titleProper = TitleProper("La valse")
                    )
                ),
                sors = listOf(SOR("Ravel"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Suite no 2 from Bacchus et Ariane")
                    )
                ),
                sors = listOf(SOR("Roussel"))
            )
        )

        val result = t.parseHeuristically(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t16() {
        val title = "Beethoven's letters / with explanatory notes by A. C. " +
                "Kalischer ; translated with pref by J. S. Shedlock ; selected " +
                "and edited by A. Eaglefield-Hull."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t17() {
        val title = "Rickshaw boy / by Lau Shaw [pseud.] ; Translated from the Chinese " +
                "by Even King [pseud.] ; sketches by Cyrus LeRoy Baldridge."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t18() {
        val title = "Joyful wisdom / with an introd. by Kurt F. Reinhardt" +
                " ; translated by Thomas Common ; Poetry versions by Paul V. " +
                "Cohn and Maude D. Petre."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
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

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t19() {
        val title = "New encyclopedia of philosophy / J. Grooten & G. Jo Steenbergen" +
                " ; with the cooperation of K. L. Bellon [and others] ; Translated from " +
                "the Dutch, edited & rev. by Edmond van den Bossche."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("New encyclopedia of philosophy")
                    )
                ),
                sors = listOf(
                    SOR("J Grooten & G Jo Steenbergen"),
                    SOR("with the cooperation of K L Bellon [and others]"),
                    SOR(
                        "Translated from the Dutch, edited & rev by Edmond " +
                                "van den Bossche"
                    )
                )
            )
        )

        val result = t.parseAll(title)

        assertTrue(result.isNotEmpty())
        assertTrue(result.contains(expected))
    }

    @Test
    fun t20() {
        val title = "Redes / Revueltas. Concerto grosso for string quartet and orchestra" +
                " / Orbon. Sense maya / Revueltas. Pampeana no. 3 / Ginastera [sound recording]."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Redes")
                    )
                ),
                sors = listOf(SOR("Revueltas"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Concerto grosso for string quartet and orchestra")
                    )
                ),
                sors = listOf(SOR("Orbon"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Sense maya")
                    )
                ),
                sors = listOf(SOR("Revueltas"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Pampeana no 3")
                    )
                ),
                sors = listOf(
                    SOR("Ginastera [sound recording]")
                )
            )
        )

        val result = t.parseHeuristically(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t21() {
        val title = "Violin concerto [sound recording] / Sibelius. Violin concerto no. 2" +
                " / Prokofiev. Violin concerto / Glazunov."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Violin concerto [sound recording]")
                    )
                ),
                sors = listOf(SOR("Sibelius"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Violin concerto no 2")
                    )
                ),
                sors = listOf(SOR("Prokofiev"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Violin concerto")
                    )
                ),
                sors = listOf(SOR("Glazunov"))
            )
        )

        val result = t.parseHeuristically(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t22() {
        val title = "Trio for violin, cello, and piano, in D minor, op. 11 (posth.)" +
                " / Fanny Mendelssohn. Tarantella, op. 6 / Saint-Saens. Paganiniana" +
                " / Arr. [by] Elayakim Taussig [sound recording]."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper(
                            "Trio for violin, cello, and " +
                                    "piano, in D minor, op 11 (posth)"
                        )
                    )
                ),
                sors = listOf(SOR("Fanny Mendelssohn"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Tarantella, op 6")
                    )
                ),
                sors = listOf(SOR("Saint-Saens"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Paganiniana")
                    )
                ),
                sors = listOf(SOR("Arr [by] Elayakim Taussig [sound recording]"))
            )
        )

        val result = t.parseHeuristically(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t23() {
        val title = "The Johannine epistles : a commentary on the Johannine epistles" +
                " / by Rudolph Bultmann ; translated by R. Philip O'Hara with Lane C. " +
                "McGaughy and Robert Funk ; edited by Robert W. Funk."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The Johannine epistles"),
                        otherInfo = listOf(
                            OtherInfo("a commentary on the Johannine epistles")
                        )
                    )
                ),
                sors = listOf(
                    SOR("by Rudolph Bultmann"),
                    SOR(
                        "translated by R Philip O'Hara with Lane C " +
                                "McGaughy and Robert Funk"
                    ),
                    SOR("edited by Robert W Funk")
                )
            )
        )

        val result = t.parseAll(title)

        assertTrue(result.isNotEmpty())
        assertTrue(result.contains(expected))
    }

    @Test
    fun t24() {
        val title = "Rhapsody in blue [sound recording] ; An American in Paris / Gershwin." +
                " Grand Canyon suite / Grofé. Prelude, fugue and riffs / Leonard Bernstein."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Rhapsody in blue [sound recording]")
                    ),
                    Monograph(
                        titleProper = TitleProper("An American in Paris")
                    )
                ),
                sors = listOf(SOR("Gershwin"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Grand Canyon suite")
                    )
                ),
                sors = listOf(SOR("Grofé"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Prelude, fugue and riffs")
                    )
                ),
                sors = listOf(SOR("Leonard Bernstein"))
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t25() {
        val title = "Russian-German settlements in the United States / Richard Sallet" +
                " ; translated by LaVern J. Rippley and Armand Bauer. Place names of " +
                "German colonies in Russia and the Dobrudja / by Armand Bauer. Prairie " +
                "architecture of the Russian-German settlers / by William C. Sherman."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Russian-German settlements in the United States")
                    )
                ),
                sors = listOf(
                    SOR("Richard Sallet"),
                    SOR("translated by LaVern J Rippley and Armand Bauer")
                )
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper(
                            "Place names of German " +
                                    "colonies in Russia and the Dobrudja"
                        )
                    )
                ),
                sors = listOf(SOR("by Armand Bauer"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper(
                            "Prairie architecture of " +
                                    "the Russian-German settlers"
                        )
                    )
                ),
                sors = listOf(SOR("by William C Sherman"))
            )
        )

        val result = t.parseAll(title)

        assertTrue(result.isNotEmpty())
        assertTrue(result.contains(expected))
    }

    @Test
    fun t26() {
        val title = "Guernica, Pablo Picasso / text by Juan Larrea ; Tr. by Alexander H. " +
                "Krappe and ed. by Walter Pach ; Introd. by Alfred H. Barr, Jr."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Guernica, Pablo Picasso")
                    )
                ),
                sors = listOf(
                    SOR("text by Juan Larrea"),
                    SOR("Tr by Alexander H Krappe and ed by Walter Pach"),
                    SOR("Introd by Alfred H Barr, Jr")
                )
            )
        )

        val result = t.parseAll(title)

        assertTrue(result.isNotEmpty())
        assertTrue(result.contains(expected))
    }

    @Test
    fun t27() {
        val title = "String quartet op. 10 [sound recording] / Claude Debussy. String " +
                "Quartet / Maurice Ravel."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("String quartet op 10 [sound recording]")
                    )
                ),
                sors = listOf(SOR("Claude Debussy"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("String Quartet")
                    )
                ),
                sors = listOf(SOR("Maurice Ravel"))
            )
        )

        val result = t.parseHeuristically(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t28() {
        val title = "Adventure time. [12], Thunder road / created by Pendleton Ward ; written " +
                "by Jeremy Sorese ; illustrated by Zachary Sterling ; colors by Laura Langston" +
                " ; letters by Mike Fiorentino."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Adventure time [12], Thunder road")
                    )
                ),
                sors = listOf(
                    SOR("created by Pendleton Ward"),
                    SOR("written by Jeremy Sorese"),
                    SOR("illustrated by Zachary Sterling"),
                    SOR("colors by Laura Langston"),
                    SOR("letters by Mike Fiorentino")
                )
            )
        )

        val result = t.parseAll(title)

        assertTrue(result.isNotEmpty())
        assertTrue(result.contains(expected))
    }

    @Test
    fun t29() {
        val title = "Three Irish plays : The moon in the Yellow River / [by] Denis " +
                "Johnston. The iron harp / Joseph O'Conor. Step-in-the-hollow / Donagh " +
                "MacDonagh."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Three Irish plays"),
                        otherInfo = listOf(
                            OtherInfo("The moon in the Yellow River")
                        )
                    )
                ),
                sors = listOf(SOR("[by] Denis Johnston"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The iron harp")
                    )
                ),
                sors = listOf(SOR("Joseph O'Conor"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Step-in-the-hollow")
                    )
                ),
                sors = listOf(SOR("Donagh MacDonagh"))
            )
        )

        val result = t.parse(title)

        assertTrue(result.isNotEmpty())
        assertEquals(expected, result)
    }

    @Test
    fun t30() {
        val title = "Bilder einer Ausstellang [sound recording] = Pictures " +
                "at an exhibition = Tableaux d'une exposition ; Eine Nacht auf " +
                "dem kahlen Berge = Night on Bald Mountain = Une nuit sur le mont " +
                "chauve / Modest Mussorgsky. Valses nobles et sentimentales / Maurice " +
                "Ravel."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Bilder einer Ausstellang [sound recording]"),
                        parallelTitles = listOf(
                            ParallelMonograph("Pictures at an exhibition"),
                            ParallelMonograph("Tableaux d'une exposition")
                        )
                    ),
                    Monograph(
                        titleProper = TitleProper("Eine Nacht auf dem kahlen Berge"),
                        parallelTitles = listOf(
                            ParallelMonograph("Night on Bald Mountain"),
                            ParallelMonograph("Une nuit sur le mont chauve")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Modest Mussorgsky")
                )
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Valses nobles et sentimentales")
                    )
                ),
                sors = listOf(
                    SOR("Maurice Ravel")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t31() {
        val title = "Fantasie in C major, op. 15 [sound recording] : Wandererfantasie" +
                " / Franz Schubert. Fantasie in C major, op. 17 / Robert Schumann."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Fantasie in C major, op 15 [sound recording]"),
                        otherInfo = listOf(
                            OtherInfo("Wandererfantasie")
                        )
                    )
                ),
                sors = listOf(SOR("Franz Schubert"))
            ),
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Fantasie in C major, op 17")
                    )
                ),
                sors = listOf(SOR("Robert Schumann"))
            )
        )

        val result = t.parseHeuristically(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t32() {
        val title = "Concerto no. 1 for piano & orchestra, op. 15, C major " +
                "[sound recording] = C-Dur = ut majeur ; Concerto no. 3 for " +
                "piano & orchestra, op. 37, C minor = c-moll = ut mineur" +
                " / Ludwig van Beethoven."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Concerto no 1 for piano & orchestra, " +
                                "op 15, C major [sound recording]"),
                        parallelTitles = listOf(
                            ParallelMonograph("C-Dur"),
                            ParallelMonograph("ut majeur")
                        )
                    ),
                    Monograph(
                        titleProper = TitleProper("Concerto no 3 for piano & " +
                                "orchestra, op 37, C minor"),
                        parallelTitles = listOf(
                            ParallelMonograph("c-moll"),
                            ParallelMonograph("ut mineur")
                        )
                    )
                ),
                sors = listOf(SOR("Ludwig van Beethoven"))
            )
        )

        val result = t.parseHeuristically(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}