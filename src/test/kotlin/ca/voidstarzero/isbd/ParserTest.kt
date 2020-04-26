package ca.voidstarzero.isbd

import org.junit.Test

class ParserTest {

    private val g: Grammar = Grammar()

    @Test
    fun t1() {
        val title = "Tous les matins du monde [videorecording] = " +
                "All the mornings of the world / Jean-Louis Livi présente ; " +
                "produit par Jean-Louis Livi ; un film de Alain Corneau."

        val expected = ISBDParsedResult(
            titles = listOf(
                TitleProper(
                    title = "Tous les matins du monde",
                    otherInfo = listOf("[videorecording]"),
                    statementsOfResp = listOf(
                        "Jean-Louis Livi présente",
                        "produit par Jean-Louis Livi",
                        "un film de Alain Corneau"
                    )
                )
            ),
            parallelTitles = listOf(
                TitleProper(
                    title = "All the mornings of the world"
                )
            )
        )
    }

    @Test
    fun t2() {
        val title = "Belgique [sound recording] : musique flamande = België : " +
                "Vlaamse volksmuziek = Belgium : Flemish folk music / " +
                "het Brabants Volksorkest \"Crispijn.\""

        val expected = ISBDParsedResult(
            titles = listOf(
                TitleProper(
                    title = "Belgique",
                    otherInfo = listOf("[sound recording]", "musique flamande"),
                    statementsOfResp = listOf("het Brabants Volksorkest \"Crispijn.\"")
                )
            ),
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
    }

    @Test
    fun t3() {
        val title = "Mercadet = The Napoleon of finance : a comedy in three acts / " +
                "by Honore de Balzac ; translated by Robert Cornthwaite."

        val expected = ISBDParsedResult(
            titles = listOf(
                TitleProper(
                    title = "Mercadet",
                    otherInfo = listOf("a comedy in three acts"),
                    statementsOfResp = listOf(
                        "by Honore de Balzac",
                        "translated by Robert Cornthwaite"
                    )
                )
            ),
            parallelTitles = listOf(
                TitleProper(
                    title = "The Napoleon of finance"
                )
            )
        )
    }

    @Test
    fun t4() {
        val title = "Hiberniae delineatio = atlas of Ireland."

        val expected = ISBDParsedResult(
            titles = listOf(
                TitleProper(
                    title = "Hiberniae delineatio"
                )
            ),
            parallelTitles = listOf(
                TitleProper(
                    title = "atlas of Ireland"
                )
            )
        )
    }

    @Test
    fun t5() {
        val title = "The modern English-Nihongo dictionary = Nihongo gakushu Ei-Nichi jiten / " +
                "executive editor: Fumio Tamamura ; executive contributor: Kakuko Shoji."
    }

    @Test
    fun t6() {
        val title = "Das grosse lehrbuch der gemüse- & früchte-schnitzerei = The complete " +
                "manual to vegetable & fruit carving = Le grand manuel de la sculpture des " +
                "légumes & des fruit = Il grande manuale dell' intaglio di verdura & frutta / " +
                "Xiang Wang, artist."
    }

    @Test
    fun t7() {
        val title = "Moreninha = The little paper doll : from A Prole do Bebe, no.1 / " +
                "Villa-Lobos ; arr. for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe."
    }

    @Test
    fun t8() {
        val title = "Overtüren = Overtures [sound recording] / Jacques Offenbach."
    }

    @Test
    fun t9() {
        val title = "Sonate d-moll : fur Altblockflote (Querflote) und Basso continuo = " +
                "Sonata D minor, for treble recorder (flute) and basso continuo, opus III, " +
                "no. 6 / Jean Baptiste (John) Loeillet ; hrsg. von Hugo Ruf."
    }

    @Test
    fun t10() {
        val title = "Die Klaviersonaten [sound recording] ; Tänze = The piano sonatas ; " +
                "Dances : complete recording / Franz Schubert."
    }

    @Test
    fun t11() {
        val title = "Russian stories = Russkie rasskazy / edited by Gleb Struve."
    }

    @Test
    fun t12() {
        val title = "The Mexican league : comprehensive player statistics, 1937-2001 = La liga " +
                "Mexicana : estadísticas comprensivas de los jugadores, 1937-2001 / Pedro Treto Cisneros."
    }

    @Test
    fun t13() {
        val title = "A source book in theatrical history = Sources of theatrical history / by A. M. Nagler."
    }

    @Test
    fun t14() {
        val title = "That's not fair! : Emma Tenayuca's struggle for justice = No es justo! : " +
                "la lucha de Emma Tenayuca por la justicia / written by Carmen Tafolla & Sharyll " +
                "Teneyuca ; illustrated by Terry Ybáñez ; Spanish translation by Carmen Tafolla."
    }

    @Test
    fun t15() {
        val title = "Chine [sound recording] : le soleil et la lune = China : the sun and the moon."
    }

    @Test
    fun t16() {
        val title = "Wo he Huojin de sheng huo = Travelling to infinity / [Ying] " +
                "Jian·Huojing (Jane Hawking) zhu ; Zhang Jing, Wang Hanmin yi."
    }

    @Test
    fun t17() {
        val title = "Perrazo y Perrito = Big Dog and Little Dog / Dav Pilkey ; traducido por Carlos E. Calvo."
    }

    @Test
    fun t18() {
        val title = "Macuilli tlachtli = Cinco deportes mexicanos = Five Mexican sports : " +
                "Cinq sports mexicains / Raziel Garcia Arroyo."
    }
}