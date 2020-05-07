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
            TitleStatement(
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
            TitleStatement(
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
            TitleStatement(
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
            TitleStatement(
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
            TitleStatement(
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
            TitleStatement(
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
        val title = "Moreninha = The little paper doll : from A Prole do Bebe, no 1 / " +
                "Villa-Lobos ; arr for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("Moreninha")),
                otherInfos = listOf(OtherInfo("from A Prole do Bebe, no 1")),
                sors = listOf(
                    SOR("Villa-Lobos"),
                    SOR("arr for 2 pianos, 4 hands by Arthur Whittemore and Jack Lowe")
                ),
                parallelTitles = listOf(ParallelTitle("The little paper doll"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t8() {
        val title = "Overtüren = Overtures [sound recording] / Jacques Offenbach."

        val expected = listOf(
            TitleStatement(
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
    fun t9() {
        val title = "Sonate d-moll : fur Altblockflote (Querflote) und Basso continuo = " +
                "Sonata D minor : for treble recorder (flute) and basso continuo, opus III, " +
                "no 6 / Jean Baptiste (John) Loeillet ; hrsg von Hugo Ruf."

        val expected = listOf(
            TitleStatement(
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

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t10() {
        val title = "Die Klaviersonaten [sound recording] ; Tänze = The piano sonatas ; " +
                "Dances : complete recording / Franz Schubert."

        val expected = listOf(
            TitleStatement(
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
    fun t11() {
        val title = "Russian stories = Russkie rasskazy / edited by Gleb Struve."

        val expected = listOf(
            TitleStatement(
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
    fun t12() {
        val title = "The Mexican league : comprehensive player statistics, 1937-2001 = La liga " +
                "Mexicana : estadísticas comprensivas de los jugadores, 1937-2001 / Pedro Treto Cisneros."

        val expected = listOf(
            TitleStatement(
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
    fun t13() {
        val title = "A source book in theatrical history = Sources of theatrical history / by A M Nagler."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("A source book in theatrical history")),
                sors = listOf(SOR("by A M Nagler")),
                parallelTitles = listOf(ParallelTitle("Sources of theatrical history"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t14() {
        val title = "That's not fair! : Emma Tenayuca's struggle for justice = No es justo! : " +
                "la lucha de Emma Tenayuca por la justicia / written by Carmen Tafolla & Sharyll " +
                "Teneyuca ; illustrated by Terry Ybáñez ; Spanish translation by Carmen Tafolla."

        val expected = listOf(
            TitleStatement(
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
    fun t15() {
        val title = "Chine [sound recording] : le soleil et la lune = China : the sun and the moon."

        val expected = listOf(
            TitleStatement(
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
    fun t16() {
        val title = "Wo he Huojin de sheng huo = Travelling to infinity / [Ying] " +
                "Jian·Huojing (Jane Hawking) zhu ; Zhang Jing, Wang Hanmin yi."

        val expected = listOf(
            TitleStatement(
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
    fun t17() {
        val title = "Perrazo y Perrito = Big Dog and Little Dog / Dav Pilkey ; traducido por Carlos E Calvo."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("Perrazo y Perrito")),
                sors = listOf(
                    SOR("Dav Pilkey"),
                    SOR("traducido por Carlos E Calvo")
                ),
                parallelTitles = listOf(ParallelTitle("Big Dog and Little Dog"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t18() {
        val title = "Macuilli tlachtli = Cinco deportes mexicanos = Five Mexican sports" +
                " = Cinq sports mexicains / Raziel Garcia Arroyo."

        val expected = listOf(
            TitleStatement(
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
    fun t19() {
        val title = "Hands are not for hitting = Las manos no son para pegar / Martine " +
                "Agassi ; illustrado por Marieka Heinlen."

        val expected = listOf(
            TitleStatement(
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
    fun t20() {
        val title = "Hulk Hogan : wrestling pro = campeon de lucha libre / Heather " +
                "Feldman ; traduccion al espanol Mauricio Velazquez de Leon."

        val expected = listOf(
            TitleStatement(
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
    fun t21() {
        val title = "The missing chancleta and other top-secret cases / by Alidis Vincente" +
                " ; cover and inside illustrations by Leonardo Mora = La chancleta perdida y " +
                "ostros casos secretos / por Alidis Vincente ; traduccion al español de Gabriela " +
                "Baeza Ventura ; ilustraciones de Leonardo Mora."

        val expected = listOf(
            TitleStatement(
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
    fun t22() {
        val title = "Bilder einer Ausstellang [sound recording] = Pictures " +
                "at an exhibition = Tableaux d'une exposition ; Eine Nacht auf " +
                "dem kahlen Berge = Night on Bald Mountain = Une nuit sur le mont " +
                "chauve / Modest Mussorgsky. Valses nobles et sentimentales / Maurice " +
                "Ravel."

        val expected = listOf(
            TitleStatement(
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
            TitleStatement(
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
    fun t23() {
        val title = "This can lick a lollipop : body riddles for kids = Esto gozo " +
                "chupando un caramelo : les partes del cuerpo en adivinanzas infantiles" +
                " / English words by Joel Rothman ; Spanish words by Argentica Palacios" +
                " ; photographs by Patricia Ruben."

        val expected = listOf(
            TitleStatement(
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
    fun t24() {
        val title = "To life [sound recording] = Le chaim! : Jewish party."

        val expected = listOf(
            TitleStatement(
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
    fun t25() {
        val title = "Long nian dang an = Dragon year file / Ke Yunlu Zhu."

        val expected = listOf(
            TitleStatement(
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
    fun t26() {
        val title = "Le Quai Des Brumes = Port of Shadows"

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("Le Quai Des Brumes")),
                parallelTitles = listOf(ParallelTitle("Port of Shadows"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t27() {
        val title = "Hui dao can zhuo, hui dao sheng huo = Life / Cai Yingqing zhu."

        val expected = listOf(
            TitleStatement(
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
    fun t28() {
        val title = "Das Kantatenwerk Vol 34 [sound recording] = Complete canatas" +
                " / Johann Sebastian Bach."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("Das Kantatenwerk Vol 34 [sound recording]")),
                sors = listOf(SOR("Johann Sebastian Bach")),
                parallelTitles = listOf(ParallelTitle("Complete canatas"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t29() {
        val title = "The new churches of Europe = Las neuvas iglesias de Europa" +
                " / [by] G E Kidder Smith."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("The new churches of Europe")),
                sors = listOf(SOR("[by] G E Kidder Smith")),
                parallelTitles = listOf(ParallelTitle("Las neuvas iglesias de Europa"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t30() {
        val title = "Good night, Mr Panda = Buenas noches, Sr Panda" +
                " / Steven Antony ; translated by JP Lombana."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("Good night, Mr Panda")),
                sors = listOf(
                    SOR("Steven Antony"),
                    SOR("translated by JP Lombana")
                ),
                parallelTitles = listOf(ParallelTitle("Buenas noches, Sr Panda"))
            )
        )

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t31() {
        val title = "Spennigens land = The land of suspense / Knut Nystedt."

        val expected = listOf(
            TitleStatement(
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
    fun t32() {
        val title = "Brown bear, brown bear, what do you see? = Oso pardo, " +
                "oso pardo, ¿qué ves ahí? / Bill Martin, Jr ; pictures by/illustraciones " +
                "de Eric Carle ; translation by/traducción de Teresa Mlawer."

        val expected = listOf(
            TitleStatement(
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

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t33() {
        val title = "Atlas the gioi khu'ng long = The Usborne Internet-linked " +
                "world atlas of dinosaurs / Thiet ke: Susanna Davidson, Stephanie Turnbull, " +
                "va Rachel Firth ; Minh hoa: Todd Marshall, Barry Croucher, Nelupa " +
                "Hussain va Glen Bird ; Nguoi dich: Viet Hoang - Viet Chung."

        val expected = listOf(
            TitleStatement(
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
    fun t34() {
        val title = "Arc-en-ciel : le plus beau poisson des océans = The rainbow fish" +
                " : the most beautiful fish in the ocean / Marcus Pfister" +
                " ; [English translation by Rosemary Lanning]"

        val expected = listOf(
            TitleStatement(
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
    fun t35() {
        val title = "Crosscurrents of modernism : four Latin American pioneers" +
                " : Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta" +
                " = Intercambios del modernismo : cuatro precursores latinoamericanos" +
                " : Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta" +
                " / Valerie Fletcher with essays by Olivier Debroise [et al]" +
                " ; [English translations by James Oles, Margaret Sayers Peden, " +
                "and Eliot Weinberger ; Spanish translations by Carlos Banales and " +
                "Carlos Tripoldi]."

        val expected = listOf(
            TitleStatement(
                titles = listOf(Title("Crosscurrents of modernism")),
                otherInfos = listOf(
                    OtherInfo("four Latin American pioneers"),
                    OtherInfo("Diego Rivera, Joaquin Torres-Garcia, Wifredo Lam, Matta")
                ),
                sors = listOf(
                    SOR("Valerie Fletcher with essays by Olivier Debroise [et al]"),
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

        val result = g.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}