package ca.voidstarzero.isbd

import org.junit.Assert.assertEquals
import org.junit.Test

class ParserTest {

    private val g: Grammar = Grammar()

    @Test
    fun t0() {
        val title = "nothing [hello] : other = rien! / by Anon"
        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t1() {
        val title = "Tous les matins du monde [videorecording] = " +
                "All the mornings of the world / Jean-Louis Livi présente ; " +
                "produit par Jean-Louis Livi ; un film de Alain Corneau."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Tous les matins du monde",
                    otherInfo = listOf("[videorecording]"),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "All the mornings of the world"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Jean-Louis Livi présente"),
                SOR("produit par Jean-Louis Livi"),
                SOR("un film de Alain Corneau")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t2() {
        val title = "Belgique [sound recording] : musique flamande = België : " +
                "Vlaamse volksmuziek = Belgium : Flemish folk music / " +
                "het Brabants Volksorkest \"Crispijn.\""

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Belgique",
                    otherInfo = listOf("[sound recording]", "musique flamande"),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "België",
                            otherInfo = listOf("Vlaamse volksmuziek")
                        ),
                        TitleProper(
                            title = "Belgium",
                            otherInfo = listOf("Flemish folk music")
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("het Brabants Volksorkest \"Crispijn.\"")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t3() {
        val title = "Mercadet = The Napoleon of finance : a comedy in three acts / " +
                "by Honore de Balzac ; translated by Robert Cornthwaite."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Mercadet",
                    otherInfo = listOf("a comedy in three acts"),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "The Napoleon of finance"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("by Honore de Balzac"),
                SOR("translated by Robert Cornthwaite")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t4() {
        val title = "Hiberniae delineatio = atlas of Ireland."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Hiberniae delineatio",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "atlas of Ireland"
                        )
                    )
                )
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t5() {
        val title = "The modern English-Nihongo dictionary = Nihongo gakushu Ei-Nichi jiten / " +
                "executive editor: Fumio Tamamura ; executive contributor: Kakuko Shoji."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "The modern English-Nihongo dictionary",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Nihongo gakushu Ei-Nichi jiten"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("executive editor: Fumio Tamamura"),
                SOR("executive contributor: Kakuko Shoji")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t6() {
        val title = "Das grosse lehrbuch der gemüse- & früchte-schnitzerei = The complete " +
                "manual to vegetable & fruit carving = Le grand manuel de la sculpture des " +
                "légumes & des fruit = Il grande manuale dell' intaglio di verdura & frutta / " +
                "Xiang Wang, artist."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Das grosse lehrbuch der gemüse- & früchte-schnitzerei",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "The complete manual to vegetable & fruit carving"
                        ),
                        TitleProper(
                            title = "Le grand manuel de la sculpture des légumes & des fruit"
                        ),
                        TitleProper(
                            title = "Il grande manuale dell' intaglio di verdura & frutta"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Xiang Wang, artist")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    //FIXME? Dependent Title??
    fun t7() {
        val title = "Moreninha = The little paper doll : from A Prole do Bebe, no.1 / " +
                "Villa-Lobos ; arr. for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Moreninha",
                    otherInfo = listOf("from A Prole do Bebe, no.1"),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "The little paper doll"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Villa-Lobos"),
                SOR("arr. for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t8() {
        val title = "Overtüren = Overtures [sound recording] / Jacques Offenbach."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Overtüren",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Overtures",
                            otherInfo = listOf("sound recording")
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Jacques Offenbach")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    //FIXME? Dependent Title??
    fun t9() {
        val title = "Sonate d-moll : fur Altblockflote (Querflote) und Basso continuo = " +
                "Sonata D minor, for treble recorder (flute) and basso continuo, opus III, " +
                "no. 6 / Jean Baptiste (John) Loeillet ; hrsg. von Hugo Ruf."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Sonate d-moll",
                    otherInfo = listOf(
                        "fur Altblockflote (Querflote) und Basso continuo"
                    ),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Sonata D minor, for treble recorder (flute) and basso continuo, opus III, no. 6"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Jean Baptiste (John) Loeillet"),
                SOR("hrsg. von Hugo Ruf")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t10() {
        val title = "Die Klaviersonaten [sound recording] ; Tänze = The piano sonatas ; " +
                "Dances : complete recording / Franz Schubert."

        // ...
        // FIXME??
        // awkward shared SOR??? T-T
        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Die Klaviersonaten",
                    otherInfo = listOf("sound recording")
                ),
                TitleProper(
                    title = "Tänze",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "The piano sonatas"
                        )
                    )
                ),
                TitleProper(
                    title = "Dances",
                    otherInfo = listOf("complete recording")
                )
            ),
            statementsOfResp = listOf(
                SOR("Franz Schubert")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t11() {
        val title = "Russian stories = Russkie rasskazy / edited by Gleb Struve."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Russian stories",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Russkie rasskazy"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("edited by Gleb Struve")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t12() {
        val title = "The Mexican league : comprehensive player statistics, 1937-2001 = La liga " +
                "Mexicana : estadísticas comprensivas de los jugadores, 1937-2001 / Pedro Treto Cisneros."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "The Mexican league",
                    otherInfo = listOf("comprehensive player statistics, 1937-2001"),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "La liga Mexicana",
                            otherInfo = listOf("estadísticas comprensivas de los jugadores, 1937-2001")
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Pedro Treto Cisneros")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t13() {
        val title = "A source book in theatrical history = Sources of theatrical history / by A. M. Nagler."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "A source book in theatrical history",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Sources of theatrical history"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("by A. M. Nagler")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t14() {
        val title = "That's not fair! : Emma Tenayuca's struggle for justice = No es justo! : " +
                "la lucha de Emma Tenayuca por la justicia / written by Carmen Tafolla & Sharyll " +
                "Teneyuca ; illustrated by Terry Ybáñez ; Spanish translation by Carmen Tafolla."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "That's not fair!",
                    otherInfo = listOf("Emma Tenayuca's struggle for justice"),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "No es justo!",
                            otherInfo = listOf("la lucha de Emma Tenayuca por la justicia")
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("written by Carmen Tafolla & Sharyll Teneyuca"),
                SOR("illustrated by Terry Ybáñez"),
                SOR("Spanish translation by Carmen Tafolla")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t15() {
        val title = "Chine [sound recording] : le soleil et la lune = China : the sun and the moon."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Chine",
                    otherInfo = listOf(
                        "sound recording",
                        "le soleil et la lune"
                    ),
                    parallelTitles = listOf(
                        TitleProper(
                            title = "China",
                            otherInfo = listOf("the sun and the moon")
                        )
                    )
                )
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t16() {
        val title = "Wo he Huojin de sheng huo = Travelling to infinity / [Ying] " +
                "Jian·Huojing (Jane Hawking) zhu ; Zhang Jing, Wang Hanmin yi."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Wo he Huojin de sheng huo",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Travelling to infinity"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("[Ying]"),
                SOR("Jian·Huojing (Jane Hawking) zhu"),
                SOR("Zhang Jing, Wang Hanmin yi")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t17() {
        val title = "Perrazo y Perrito = Big Dog and Little Dog / Dav Pilkey ; traducido por Carlos E. Calvo."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Perrazo y Perrito",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Big Dog and Little Dog"
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Dav Pilkey"),
                SOR("traducido por Carlos E. Calvo")
            )
        )

        val result = g.parse(title)
        println(result)
    }

    @Test
    fun t18() {
        val title = "Macuilli tlachtli = Cinco deportes mexicanos = Five Mexican sports : " +
                "Cinq sports mexicains / Raziel Garcia Arroyo."

        val expected = ISBDParseResult(
            titles = listOf(
                TitleProper(
                    title = "Macuilli tlachtli",
                    parallelTitles = listOf(
                        TitleProper(
                            title = "Cinco deportes mexicanos"
                        ),
                        TitleProper(
                            title = "Five Mexican sports",
                            otherInfo = listOf("Cinq sports mexicains")
                        )
                    )
                )
            ),
            statementsOfResp = listOf(
                SOR("Raziel Garcia Arroyo")
            )
        )

        val result = g.parse(title)
        println(result)
    }
}