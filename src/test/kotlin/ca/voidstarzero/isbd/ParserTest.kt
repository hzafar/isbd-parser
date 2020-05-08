package ca.voidstarzero.isbd

import ca.voidstarzero.isbd.ast.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ParserTest {

    private val g: Grammar = Grammar()

    @Test
    fun t1() {
        val title = "Tous les matins du monde [videorecording] = " +
                "All the mornings of the world / Jean-Louis Livi présente" +
                " ; produit par Jean-Louis Livi ; un film de Alain Corneau."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Tous les matins du monde [videorecording]")),
                parallelTitles = listOf(ParallelTitle("All the mornings of the world")),
                sors = listOf(
                    SOR("Jean-Louis Livi présente"),
                    SOR("produit par Jean-Louis Livi"),
                    SOR("un film de Alain Corneau")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("Belgique [sound recording]")),
                otherInfos = listOf(OtherInfo("musique flamande")),
                sors = listOf(SOR("het Brabants Volksorkest \"Crispijn\"")),
                parallelTitles = listOf(
                    ParallelTitle("België"),
                    ParallelTitle("Belgium")
                ),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("Vlaamse volksmuziek"),
                    ParallelOtherInfo("Flemish folk music")
                ),
                parallelSORs = listOf()
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t3() {
        val title = "Mercadet = The Napoleon of finance : a comedy in three acts / " +
                "by Honore de Balzac ; translated by Robert Cornthwaite."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Mercadet")),
                otherInfos = listOf(OtherInfo("a comedy in three acts")),
                sors = listOf(
                    SOR("by Honore de Balzac"),
                    SOR("translated by Robert Cornthwaite")
                ),
                parallelTitles = listOf(ParallelTitle("The Napoleon of finance"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t4() {
        val title = "Hiberniae delineatio = atlas of Ireland."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Hiberniae delineatio")),
                parallelTitles = listOf(ParallelTitle("atlas of Ireland"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t5() {
        val title = "The modern English-Nihongo dictionary = Nihongo gakushu Ei-Nichi jiten / " +
                "executive editor: Fumio Tamamura ; executive contributor: Kakuko Shoji."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("The modern English-Nihongo dictionary")),
                sors = listOf(
                    SOR("executive editor: Fumio Tamamura"),
                    SOR("executive contributor: Kakuko Shoji")
                ),
                parallelTitles = listOf(ParallelTitle("Nihongo gakushu Ei-Nichi jiten"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("Das grosse lehrbuch der gemüse- & früchte-schnitzerei")),
                sors = listOf(SOR("Xiang Wang, artist")),
                parallelTitles = listOf(
                    ParallelTitle("The complete manual to vegetable & fruit carving"),
                    ParallelTitle("Le grand manuel de la sculpture des légumes & des fruit"),
                    ParallelTitle("Il grande manuale dell' intaglio di verdura & frutta")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t7() {
        val title = "Overtüren = Overtures [sound recording] / Jacques Offenbach."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Overtüren")),
                sors = listOf(SOR("Jacques Offenbach")),
                parallelTitles = listOf(ParallelTitle("Overtures [sound recording]"))
            )
        )

        val result = g.parse(title)

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
                    Title("Die Klaviersonaten [sound recording]"),
                    Title("Tänze"),
                    Title("Dances")
                ),
                otherInfos = listOf(OtherInfo("complete recording")),
                sors = listOf(SOR("Franz Schubert")),
                parallelTitles = listOf(
                    ParallelTitle("The piano sonatas")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t9() {
        val title = "Russian stories = Russkie rasskazy / edited by Gleb Struve."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Russian stories")),
                sors = listOf(SOR("edited by Gleb Struve")),
                parallelTitles = listOf(ParallelTitle("Russkie rasskazy"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t10() {
        val title = "The Mexican league : comprehensive player statistics, 1937-2001 = La liga " +
                "Mexicana : estadísticas comprensivas de los jugadores, 1937-2001 / Pedro Treto Cisneros."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("The Mexican league")),
                otherInfos = listOf(OtherInfo("comprehensive player statistics, 1937-2001")),
                sors = listOf(SOR("Pedro Treto Cisneros")),
                parallelTitles = listOf(ParallelTitle("La liga Mexicana")),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("estadísticas comprensivas de los jugadores, 1937-2001")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("That's not fair!")),
                otherInfos = listOf(OtherInfo("Emma Tenayuca's struggle for justice")),
                sors = listOf(
                    SOR("written by Carmen Tafolla & Sharyll Teneyuca"),
                    SOR("illustrated by Terry Ybáñez"),
                    SOR("Spanish translation by Carmen Tafolla")
                ),
                parallelTitles = listOf(ParallelTitle("No es justo!")),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("la lucha de Emma Tenayuca por la justicia")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t12() {
        val title = "Chine [sound recording] : le soleil et la lune = China : the sun and the moon."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Chine [sound recording]")),
                otherInfos = listOf(OtherInfo("le soleil et la lune")),
                parallelTitles = listOf(ParallelTitle("China")),
                parallelOtherInfos = listOf(ParallelOtherInfo("the sun and the moon"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t13() {
        val title = "Wo he Huojin de sheng huo = Travelling to infinity / [Ying] " +
                "Jian·Huojing (Jane Hawking) zhu ; Zhang Jing, Wang Hanmin yi."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Wo he Huojin de sheng huo")),
                sors = listOf(
                    SOR("[Ying] Jian·Huojing (Jane Hawking) zhu"),
                    SOR("Zhang Jing, Wang Hanmin yi")
                ),
                parallelTitles = listOf(ParallelTitle("Travelling to infinity"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t14() {
        val title = "Macuilli tlachtli = Cinco deportes mexicanos = Five Mexican sports" +
                " = Cinq sports mexicains / Raziel Garcia Arroyo."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Macuilli tlachtli")),
                sors = listOf(SOR("Raziel Garcia Arroyo")),
                parallelTitles = listOf(
                    ParallelTitle("Cinco deportes mexicanos"),
                    ParallelTitle("Five Mexican sports"),
                    ParallelTitle("Cinq sports mexicains")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t15() {
        val title = "Hands are not for hitting = Las manos no son para pegar / Martine " +
                "Agassi ; illustrado por Marieka Heinlen."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Hands are not for hitting")),
                sors = listOf(
                    SOR("Martine Agassi"),
                    SOR("illustrado por Marieka Heinlen")
                ),
                parallelTitles = listOf(ParallelTitle("Las manos no son para pegar"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t16() {
        val title = "Hulk Hogan : wrestling pro = campeon de lucha libre / Heather " +
                "Feldman ; traduccion al espanol Mauricio Velazquez de Leon."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Hulk Hogan")),
                otherInfos = listOf(OtherInfo("wrestling pro")),
                sors = listOf(
                    SOR("Heather Feldman"),
                    SOR("traduccion al espanol Mauricio Velazquez de Leon")
                ),
                parallelOtherInfos = listOf(ParallelOtherInfo("campeon de lucha libre"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("The missing chancleta and other top-secret cases")),
                sors = listOf(
                    SOR("by Alidis Vincente"),
                    SOR("cover and inside illustrations by Leonardo Mora")
                ),
                parallelTitles = listOf(
                    ParallelTitle("La chancleta perdida y ostros casos secretos")
                ),
                parallelSORs = listOf(
                    ParallelSOR("por Alidis Vincente"),
                    ParallelSOR("traduccion al español de Gabriela Baeza Ventura"),
                    ParallelSOR("ilustraciones de Leonardo Mora")
                )
            )
        )

        val result = g.parse(title)

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
                    Title("Bilder einer Ausstellang [sound recording]"),
                    Title("Eine Nacht auf dem kahlen Berge")
                ),
                sors = listOf(
                    SOR("Modest Mussorgsky")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Pictures at an exhibition"),
                    ParallelTitle("Tableaux d'une exposition"),
                    ParallelTitle("Night on Bald Mountain"),
                    ParallelTitle("Une nuit sur le mont chauve")
                )
            ),
            TitleStatementNode(
                titles = listOf(
                    Title("Valses nobles et sentimentales")
                ),
                sors = listOf(
                    SOR("Maurice Ravel")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("This can lick a lollipop")),
                otherInfos = listOf(OtherInfo("body riddles for kids")),
                sors = listOf(
                    SOR("English words by Joel Rothman"),
                    SOR("Spanish words by Argentica Palacios"),
                    SOR("photographs by Patricia Ruben")
                ),
                parallelTitles = listOf(ParallelTitle("Esto gozo chupando un caramelo")),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("les partes del cuerpo en adivinanzas infantiles")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t20() {
        val title = "To life [sound recording] = Le chaim! : Jewish party."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("To life [sound recording]")),
                otherInfos = listOf(OtherInfo("Jewish party")),
                parallelTitles = listOf(ParallelTitle("Le chaim!"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t21() {
        val title = "Long nian dang an = Dragon year file / Ke Yunlu Zhu."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Long nian dang an")),
                sors = listOf(SOR("Ke Yunlu Zhu")),
                parallelTitles = listOf(ParallelTitle("Dragon year file"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t22() {
        val title = "Le Quai Des Brumes = Port of Shadows"

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Le Quai Des Brumes")),
                parallelTitles = listOf(ParallelTitle("Port of Shadows"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t23() {
        val title = "Hui dao can zhuo, hui dao sheng huo = Life / Cai Yingqing zhu."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Hui dao can zhuo, hui dao sheng huo")),
                sors = listOf(SOR("Cai Yingqing zhu")),
                parallelTitles = listOf(ParallelTitle("Life"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t24() {
        val title = "Spennigens land = The land of suspense / Knut Nystedt."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Spennigens land")),
                sors = listOf(SOR("Knut Nystedt")),
                parallelTitles = listOf(ParallelTitle("The land of suspense"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("Atlas the gioi khu'ng long")),
                sors = listOf(
                    SOR("Thiet ke: Susanna Davidson, Stephanie Turnbull, va Rachel Firth"),
                    SOR("Minh hoa: Todd Marshall, Barry Croucher, Nelupa Hussain va Glen Bird"),
                    SOR("Nguoi dich: Viet Hoang - Viet Chung")
                ),
                parallelTitles = listOf(
                    ParallelTitle("The Usborne Internet-linked world atlas of dinosaurs")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("Arc-en-ciel")),
                otherInfos = listOf(OtherInfo("le plus beau poisson des océans")),
                sors = listOf(
                    SOR("Marcus Pfister"),
                    SOR("[English translation by Rosemary Lanning]")
                ),
                parallelTitles = listOf(ParallelTitle("The rainbow fish")),
                parallelOtherInfos = listOf(
                    ParallelOtherInfo("the most beautiful fish in the ocean")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t27() {
        val title = "Animals = Animales : English-Spanish / [designed by Hakan Şan Borteçin]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Animals")),
                otherInfos = listOf(OtherInfo("English-Spanish")),
                sors = listOf(SOR("[designed by Hakan Şan Borteçin]")),
                parallelTitles = listOf(ParallelTitle("Animales"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("aal-Ghurāb al-miskīn")),
                sors = listOf(
                    SOR("ci'dād Sālim Shams al-Dīn"),
                    SOR("al-tarjamah ilá al-Inklīzīyah Shirīn al-Shammā', " +
                            "rusūm al-fannān Nabīl Qaddūh")
                ),
                parallelTitles = listOf(ParallelTitle("The Poor crow"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t29() {
        val title = "Xiao xiao xiao xiao de huo / [Mei] Wu Qishi zhu" +
                " ; Sun Lu yi = Little fires everywhere / Celeste Ng."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Xiao xiao xiao xiao de huo")),
                sors = listOf(
                    SOR("[Mei] Wu Qishi zhu"),
                    SOR("Sun Lu yi")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Little fires everywhere")
                ),
                parallelSORs = listOf(ParallelSOR("Celeste Ng"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("A collection of Latin American folksongs")),
                sors = listOf(
                    SOR("[edited] by Raquel González Paraíso and Francisco López")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Collección de cancionces folklóricas Latinoamericanas")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t31() {
        val title = "Opposites = Zheng fan, English-Chinese / [designed by Hakan Şan Borteçin]."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Opposites")),
                sors = listOf(SOR("[designed by Hakan Şan Borteçin]")),
                parallelTitles = listOf(ParallelTitle("Zheng fan, English-Chinese"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("¿Qué hay en el cielo, querido dragón?")),
                sors = listOf(
                    SOR("por/by Margaret Hillert"),
                    SOR("illustrado por/illustrated by David Schimmell"),
                    SOR("traducio por Queta Fernandez")
                ),
                parallelTitles = listOf(ParallelTitle("What's in the sky, dear dragon?"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("¿Qué hay en el bosque, querido dragón?")),
                sors = listOf(
                    SOR("por/by Margaret Hillert"),
                    SOR("illustrado por/illustrated by David Schimmel"),
                    SOR("traducido par Queta Fernandez")
                ),
                parallelTitles = listOf(
                    ParallelTitle("What's in the woods, dear dragon?")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("How will we get to the beach?")),
                sors = listOf(
                    SOR("Brigitte Luciani"),
                    SOR("illustrated by Eve Tharlet"),
                    SOR("[Spanish translation by André Antreasyan]")
                ),
                parallelTitles = listOf(ParallelTitle("Cómo iremos a la playa?"))
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("The world's most beautiful libraries")),
                sors = listOf(
                    SOR("Massimo Listri"),
                    SOR("text by Georg Ruppelt & Elisabeth Sladek"),
                    SOR("English translation, Karen Williams"),
                    SOR("French translation, Aude Fondard")
                ),
                parallelTitles = listOf(
                    ParallelTitle("Die schönsten Bibliotheken der Welt"),
                    ParallelTitle("Les plus belles bibliothèques du monde")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("Mia madre")),
                sors = listOf(
                    SOR("Nanni Moretti, Domenico Procacci, RAI Cinema presentano"),
                    SOR("una coproduzione italo-francese Sacher Film, " +
                            "Fandango con RAI Cinema, Le Pacte, Arte France Cinéma"),
                    SOR("un film de Nanni Moretti"),
                    SOR("sogetto, Gaia Manzini, Nanni Moretti, Valia Santella, Chiara Valerio"),
                    SOR("sceneggiatura, Nanni Moretti, Francesco Piccolo, Valia Santanella"),
                    SOR("regia, Nanni Moretti"),
                    SOR("prodotto da Nanni Moretti e Domenico Procacci")
                ),
                parallelTitles = listOf(ParallelTitle("My mother"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t37() {
        val title = "Howdy, honey, howdy : illustrated with photos / by Leigh " +
                "Richmond Miner ; decorations by Will Jenkins."

        val expected = listOf(
            TitleStatementNode(
                titles = listOf(Title("Howdy, honey, howdy")),
                otherInfos = listOf(OtherInfo("illustrated with photos")),
                sors = listOf(
                    SOR("by Leigh Richmond Miner"),
                    SOR("decorations by Will Jenkins")
                )
            )
        )

        val result = g.parse(title)

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
                titles = listOf(Title("The Penkovskiy papers")),
                sors = listOf(
                    SOR("by Oleg Penkovsky"),
                    SOR("introduction and commentary by Frank Gibney"),
                    SOR("foreword by Edward Crankshaw"),
                    SOR("translated by Peter Deriabin")
                )
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}