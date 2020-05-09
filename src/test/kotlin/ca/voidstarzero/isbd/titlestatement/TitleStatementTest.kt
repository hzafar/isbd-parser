package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TitleStatementTest {

    private val t: TitleStatement = TitleStatement()

    @Test
    fun t1() {
        val title = "Tous les matins du monde [videorecording] = " +
                "All the mornings of the world / Jean-Louis Livi présente" +
                " ; produit par Jean-Louis Livi ; un film de Alain Corneau."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Tous les matins du monde [videorecording]"),
                        parallelTitles = listOf(
                            ParallelTitle("All the mornings of the world")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Jean-Louis Livi présente"),
                    SOR("produit par Jean-Louis Livi"),
                    SOR("un film de Alain Corneau")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t2() {
        val title = "Belgique [sound recording] : musique flamande = België : " +
                "Vlaamse volksmuziek = Belgium : Flemish folk music / " +
                "het Brabants Volksorkest \"Crispijn\"."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Belgique [sound recording]"),
                        otherInfo = listOf(OtherInfo("musique flamande")),
                        parallelTitles = listOf(
                            ParallelTitle(
                                title = "België",
                                otherInfo = listOf(
                                    ParallelOtherInfo("Vlaamse volksmuziek")
                                )
                            ),
                            ParallelTitle(
                                title = "Belgium",
                                otherInfo = listOf(
                                    ParallelOtherInfo("Flemish folk music")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(SOR("het Brabants Volksorkest \"Crispijn\""))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t3() {
        val title = "Mercadet = The Napoleon of finance : a comedy in three acts / " +
                "by Honore de Balzac ; translated by Robert Cornthwaite."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Mercadet"),
                        otherInfo = listOf(OtherInfo("a comedy in three acts")),
                        parallelTitles = listOf(ParallelTitle("The Napoleon of finance"))
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
    fun t4() {
        val title = "Hiberniae delineatio = atlas of Ireland."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Hiberniae delineatio"),
                        parallelTitles = listOf(ParallelTitle("atlas of Ireland"))
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
        val title = "The modern English-Nihongo dictionary = Nihongo gakushu Ei-Nichi jiten / " +
                "executive editor: Fumio Tamamura ; executive contributor: Kakuko Shoji."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("The modern English-Nihongo dictionary"),
                        parallelTitles = listOf(
                            ParallelTitle("Nihongo gakushu Ei-Nichi jiten")
                        )
                    )
                ),
                sors = listOf(
                    SOR("executive editor: Fumio Tamamura"),
                    SOR("executive contributor: Kakuko Shoji")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t6() {
        val title = "Das grosse lehrbuch der gemüse- & früchte-schnitzerei = The complete " +
                "manual to vegetable & fruit carving = Le grand manuel de la sculpture des " +
                "légumes & des fruit = Il grande manuale dell' intaglio di verdura & frutta / " +
                "Xiang Wang, artist."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Das grosse lehrbuch der gemüse- & früchte-schnitzerei"),
                        parallelTitles = listOf(
                            ParallelTitle("The complete manual to vegetable & fruit carving"),
                            ParallelTitle("Le grand manuel de la sculpture des légumes & des fruit"),
                            ParallelTitle("Il grande manuale dell' intaglio di verdura & frutta")
                        )
                    )
                ),
                sors = listOf(SOR("Xiang Wang, artist"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t7() {
        val title = "Overtüren = Overtures [sound recording] / Jacques Offenbach."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Overtüren"),
                        parallelTitles = listOf(ParallelTitle("Overtures [sound recording]"))
                    )
                ),
                sors = listOf(SOR("Jacques Offenbach"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }


    @Test
    fun t8() {
        val title = "Die Klaviersonaten [sound recording] ; Tänze = The piano sonatas ; " +
                "Dances : complete recording / Franz Schubert."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Die Klaviersonaten [sound recording]")
                    ),
                    Title(
                        titleProper = TitleProper("Tänze"),
                        parallelTitles = listOf(ParallelTitle("The piano sonatas"))
                    ),
                    Title(
                        titleProper = TitleProper("Dances"),
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
    fun t9() {
        val title = "Russian stories = Russkie rasskazy / edited by Gleb Struve."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Russian stories"),
                        parallelTitles = listOf(ParallelTitle("Russkie rasskazy"))
                    )
                ),
                sors = listOf(SOR("edited by Gleb Struve"))
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
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("The Mexican league"),
                        otherInfo = listOf(OtherInfo("comprehensive player statistics, 1937-2001")),
                        parallelTitles = listOf(
                            ParallelTitle(
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
    fun t11() {
        val title = "That's not fair! : Emma Tenayuca's struggle for justice = No es justo! : " +
                "la lucha de Emma Tenayuca por la justicia / written by Carmen Tafolla & Sharyll " +
                "Teneyuca ; illustrated by Terry Ybáñez ; Spanish translation by Carmen Tafolla."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("That's not fair!"),
                        otherInfo = listOf(
                            OtherInfo("Emma Tenayuca's struggle for justice")
                        ),
                        parallelTitles = listOf(
                            ParallelTitle(
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

    @Test
    fun t12() {
        val title = "Chine [sound recording] : le soleil et la lune = China : the sun and the moon."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Chine [sound recording]"),
                        otherInfo = listOf(OtherInfo("le soleil et la lune")),
                        parallelTitles = listOf(
                            ParallelTitle(
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
    fun t13() {
        val title = "Wo he Huojin de sheng huo = Travelling to infinity / [Ying] " +
                "Jian·Huojing (Jane Hawking) zhu ; Zhang Jing, Wang Hanmin yi."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Wo he Huojin de sheng huo"),
                        parallelTitles = listOf(ParallelTitle("Travelling to infinity"))
                    )
                ),
                sors = listOf(
                    SOR("[Ying] Jian·Huojing (Jane Hawking) zhu"),
                    SOR("Zhang Jing, Wang Hanmin yi")
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
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Macuilli tlachtli"),
                        parallelTitles = listOf(
                            ParallelTitle("Cinco deportes mexicanos"),
                            ParallelTitle("Five Mexican sports"),
                            ParallelTitle("Cinq sports mexicains")
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

    @Test
    fun t15() {
        val title = "Hands are not for hitting = Las manos no son para pegar / Martine " +
                "Agassi ; illustrado por Marieka Heinlen."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Hands are not for hitting"),
                        parallelTitles = listOf(ParallelTitle("Las manos no son para pegar"))
                    )
                ),
                sors = listOf(
                    SOR("Martine Agassi"),
                    SOR("illustrado por Marieka Heinlen")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t16() {
        val title = "Hulk Hogan : wrestling pro = campeon de lucha libre / Heather " +
                "Feldman ; traduccion al espanol Mauricio Velazquez de Leon."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Hulk Hogan"),
                        otherInfo = listOf(OtherInfo("wrestling pro")),
                        parallelOtherInfo = listOf(
                            ParallelOtherInfo("campeon de lucha libre")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Heather Feldman"),
                    SOR("traduccion al espanol Mauricio Velazquez de Leon")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t17() {
        val title = "The missing chancleta and other top-secret cases / by Alidis Vincente" +
                " ; cover and inside illustrations by Leonardo Mora = La chancleta perdida y " +
                "ostros casos secretos / por Alidis Vincente ; traduccion al español de Gabriela " +
                "Baeza Ventura ; ilustraciones de Leonardo Mora."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("The missing chancleta and other top-secret cases")
                    )
                ),
                sors = listOf(
                    SOR("by Alidis Vincente"),
                    SOR("cover and inside illustrations by Leonardo Mora")
                ),
                parallelTitles = listOf(
                    ParallelTitle(
                        title = "La chancleta perdida y ostros casos secretos",
                        sors = listOf(
                            ParallelSOR("por Alidis Vincente"),
                            ParallelSOR("traduccion al español de Gabriela Baeza Ventura"),
                            ParallelSOR("ilustraciones de Leonardo Mora")
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
    fun t18() {
        val title = "Bilder einer Ausstellang [sound recording] = Pictures " +
                "at an exhibition = Tableaux d'une exposition ; Eine Nacht auf " +
                "dem kahlen Berge = Night on Bald Mountain = Une nuit sur le mont " +
                "chauve / Modest Mussorgsky. Valses nobles et sentimentales / Maurice " +
                "Ravel."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Bilder einer Ausstellang [sound recording]"),
                        parallelTitles = listOf(
                            ParallelTitle("Pictures at an exhibition"),
                            ParallelTitle("Tableaux d'une exposition")
                        )
                    ),
                    Title(
                        titleProper = TitleProper("Eine Nacht auf dem kahlen Berge"),
                        parallelTitles = listOf(
                            ParallelTitle("Night on Bald Mountain"),
                            ParallelTitle("Une nuit sur le mont chauve")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Modest Mussorgsky")
                )
            ),
            TitleStatementNode(
                titles = listOf(
                    Title(
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
    fun t19() {
        val title = "This can lick a lollipop : body riddles for kids = Esto gozo " +
                "chupando un caramelo : les partes del cuerpo en adivinanzas infantiles" +
                " / English words by Joel Rothman ; Spanish words by Argentica Palacios" +
                " ; photographs by Patricia Ruben."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("This can lick a lollipop"),
                        otherInfo = listOf(OtherInfo("body riddles for kids")),
                        parallelTitles = listOf(
                            ParallelTitle(
                                title = "Esto gozo chupando un caramelo",
                                otherInfo = listOf(
                                    ParallelOtherInfo("les partes del cuerpo en adivinanzas infantiles")
                                )
                            )
                        )
                    )
                ),
                sors = listOf(
                    SOR("English words by Joel Rothman"),
                    SOR("Spanish words by Argentica Palacios"),
                    SOR("photographs by Patricia Ruben")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t20() {
        val title = "To life [sound recording] = Le chaim! : Jewish party."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("To life [sound recording]"),
                        otherInfo = listOf(OtherInfo("Jewish party")),
                        parallelTitles = listOf(ParallelTitle("Le chaim!"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t21() {
        val title = "Long nian dang an = Dragon year file / Ke Yunlu Zhu."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Long nian dang an"),
                        parallelTitles = listOf(ParallelTitle("Dragon year file"))
                    )
                ),
                sors = listOf(SOR("Ke Yunlu Zhu"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t22() {
        val title = "Le Quai Des Brumes = Port of Shadows"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Le Quai Des Brumes"),
                        parallelTitles = listOf(ParallelTitle("Port of Shadows"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t23() {
        val title = "Hui dao can zhuo, hui dao sheng huo = Life / Cai Yingqing zhu."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Hui dao can zhuo, hui dao sheng huo"),
                        parallelTitles = listOf(ParallelTitle("Life"))
                    )
                ),
                sors = listOf(SOR("Cai Yingqing zhu"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t24() {
        val title = "Spennigens land = The land of suspense / Knut Nystedt."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Spennigens land"),
                        parallelTitles = listOf(ParallelTitle("The land of suspense"))
                    )
                ),
                sors = listOf(SOR("Knut Nystedt"))

            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t25() {
        val title = "Atlas the gioi khu'ng long = The Usborne Internet-linked " +
                "world atlas of dinosaurs / Thiet ke: Susanna Davidson, Stephanie Turnbull, " +
                "va Rachel Firth ; Minh hoa: Todd Marshall, Barry Croucher, Nelupa " +
                "Hussain va Glen Bird ; Nguoi dich: Viet Hoang - Viet Chung."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Atlas the gioi khu'ng long"),
                        parallelTitles = listOf(
                            ParallelTitle("The Usborne Internet-linked world atlas of dinosaurs")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Thiet ke: Susanna Davidson, Stephanie Turnbull, va Rachel Firth"),
                    SOR("Minh hoa: Todd Marshall, Barry Croucher, Nelupa Hussain va Glen Bird"),
                    SOR("Nguoi dich: Viet Hoang - Viet Chung")
                )
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
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Arc-en-ciel"),
                        otherInfo = listOf(OtherInfo("le plus beau poisson des océans")),
                        parallelTitles = listOf(
                            ParallelTitle(
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
    fun t27() {
        val title = "Animals = Animales : English-Spanish / [designed by Hakan Şan Borteçin]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Animals"),
                        otherInfo = listOf(OtherInfo("English-Spanish")),
                        parallelTitles = listOf(ParallelTitle("Animales"))
                    )
                ),
                sors = listOf(SOR("[designed by Hakan Şan Borteçin]"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t28() {
        val title = "aal-Ghurāb al-miskīn = The Poor crow / ci'dād Sālim Shams al-Dīn" +
                " ; al-tarjamah ilá al-Inklīzīyah Shirīn al-Shammā', rusūm al-fannān " +
                "Nabīl Qaddūh."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("aal-Ghurāb al-miskīn"),
                        parallelTitles = listOf(ParallelTitle("The Poor crow"))
                    )
                ),
                sors = listOf(
                    SOR("ci'dād Sālim Shams al-Dīn"),
                    SOR(
                        "al-tarjamah ilá al-Inklīzīyah Shirīn al-Shammā', " +
                                "rusūm al-fannān Nabīl Qaddūh"
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t29() {
        val title = "Xiao xiao xiao xiao de huo / [Mei] Wu Qishi zhu" +
                " ; Sun Lu yi = Little fires everywhere / Celeste Ng."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Xiao xiao xiao xiao de huo")
                    )
                ),
                sors = listOf(
                    SOR("[Mei] Wu Qishi zhu"),
                    SOR("Sun Lu yi")
                ),
                parallelTitles = listOf(
                    ParallelTitle(
                        title = "Little fires everywhere",
                        sors = listOf(ParallelSOR("Celeste Ng"))
                    )
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t30() {
        val title = "A collection of Latin American folksongs = Collección de " +
                "cancionces folklóricas Latinoamericanas / [edited] by Raquel " +
                "González Paraíso and Francisco López."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("A collection of Latin American folksongs"),
                        parallelTitles = listOf(
                            ParallelTitle("Collección de cancionces folklóricas Latinoamericanas")
                        )
                    )
                ),
                sors = listOf(
                    SOR("[edited] by Raquel González Paraíso and Francisco López")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t31() {
        val title = "Opposites = Zheng fan, English-Chinese / [designed by Hakan Şan Borteçin]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Opposites"),
                        parallelTitles = listOf(ParallelTitle("Zheng fan, English-Chinese"))
                    )
                ),
                sors = listOf(SOR("[designed by Hakan Şan Borteçin]"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t32() {
        val title = "¿Qué hay en el cielo, querido dragón? = What's in the sky, dear dragon?" +
                " / por/by Margaret Hillert ; illustrado por/illustrated by David Schimmell" +
                " ; traducio por Queta Fernandez."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("¿Qué hay en el cielo, querido dragón?"),
                        parallelTitles = listOf(ParallelTitle("What's in the sky, dear dragon?"))
                    )
                ),
                sors = listOf(
                    SOR("por/by Margaret Hillert"),
                    SOR("illustrado por/illustrated by David Schimmell"),
                    SOR("traducio por Queta Fernandez")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t33() {
        val title = "¿Qué hay en el bosque, querido dragón? = What's in the woods, " +
                "dear dragon? / por/by Margaret Hillert ; illustrado por/illustrated " +
                "by David Schimmel ; traducido par Queta Fernandez."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("¿Qué hay en el bosque, querido dragón?"),
                        parallelTitles = listOf(
                            ParallelTitle("What's in the woods, dear dragon?")
                        )
                    )
                ),
                sors = listOf(
                    SOR("por/by Margaret Hillert"),
                    SOR("illustrado por/illustrated by David Schimmel"),
                    SOR("traducido par Queta Fernandez")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t34() {
        val title = "How will we get to the beach? = Cómo iremos a la playa?" +
                " / Brigitte Luciani ; illustrated by Eve Tharlet ; [Spanish " +
                "translation by André Antreasyan]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("How will we get to the beach?"),
                        parallelTitles = listOf(ParallelTitle("Cómo iremos a la playa?"))
                    )
                ),
                sors = listOf(
                    SOR("Brigitte Luciani"),
                    SOR("illustrated by Eve Tharlet"),
                    SOR("[Spanish translation by André Antreasyan]")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t35() {
        val title = "The world's most beautiful libraries = Die schönsten Bibliotheken " +
                "der Welt = Les plus belles bibliothèques du monde / Massimo Listri" +
                " ; text by Georg Ruppelt & Elisabeth Sladek ; English translation, Karen " +
                "Williams ; French translation, Aude Fondard."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("The world's most beautiful libraries"),
                        parallelTitles = listOf(
                            ParallelTitle("Die schönsten Bibliotheken der Welt"),
                            ParallelTitle("Les plus belles bibliothèques du monde")
                        )
                    )
                ),
                sors = listOf(
                    SOR("Massimo Listri"),
                    SOR("text by Georg Ruppelt & Elisabeth Sladek"),
                    SOR("English translation, Karen Williams"),
                    SOR("French translation, Aude Fondard")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t36() {
        val title = "Mia madre = My mother / Nanni Moretti, Domenico Procacci, " +
                "RAI Cinema presentano ; una coproduzione italo-francese Sacher Film, " +
                "Fandango con RAI Cinema, Le Pacte, Arte France Cinéma ; un film de " +
                "Nanni Moretti ; sogetto, Gaia Manzini, Nanni Moretti, Valia Santella, " +
                "Chiara Valerio ; sceneggiatura, Nanni Moretti, Francesco Piccolo, Valia " +
                "Santanella ; regia, Nanni Moretti ; prodotto da Nanni Moretti e " +
                "Domenico Procacci."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Mia madre"),
                        parallelTitles = listOf(ParallelTitle("My mother"))
                    )
                ),
                sors = listOf(
                    SOR("Nanni Moretti, Domenico Procacci, RAI Cinema presentano"),
                    SOR(
                        "una coproduzione italo-francese Sacher Film, " +
                                "Fandango con RAI Cinema, Le Pacte, Arte France Cinéma"
                    ),
                    SOR("un film de Nanni Moretti"),
                    SOR("sogetto, Gaia Manzini, Nanni Moretti, Valia Santella, Chiara Valerio"),
                    SOR("sceneggiatura, Nanni Moretti, Francesco Piccolo, Valia Santanella"),
                    SOR("regia, Nanni Moretti"),
                    SOR("prodotto da Nanni Moretti e Domenico Procacci")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t37() {
        val title = "Howdy, honey, howdy : illustrated with photos / by Leigh " +
                "Richmond Miner ; decorations by Will Jenkins."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("Howdy, honey, howdy"),
                        otherInfo = listOf(OtherInfo("illustrated with photos"))
                    )
                ),
                sors = listOf(
                    SOR("by Leigh Richmond Miner"),
                    SOR("decorations by Will Jenkins")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t38() {
        val title = "The Penkovskiy papers / by Oleg Penkovsky" +
                " ; introduction and commentary by Frank Gibney" +
                " ; foreword by Edward Crankshaw" +
                " ; translated by Peter Deriabin."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(
                    Title(
                        titleProper = TitleProper("The Penkovskiy papers")
                    )
                ),
                sors = listOf(
                    SOR("by Oleg Penkovsky"),
                    SOR("introduction and commentary by Frank Gibney"),
                    SOR("foreword by Edward Crankshaw"),
                    SOR("translated by Peter Deriabin")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}