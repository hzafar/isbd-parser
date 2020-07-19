package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class SORTest {

    private val t: TitleStatementParser =
        TitleStatementParser()

    /******************************************************************
     *
     *  "Title proper = Parallel title / statement of responsibility"
     */

    @Test
    fun t7() {
        val title = "Overtüren = Overtures [sound recording] / Jacques Offenbach."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Overtüren"),
                        parallelTitles = listOf(ParallelMonograph("Overtures [sound recording]"))
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
    fun t3() {
        val title = "Das grosse lehrbuch der gemüse- & früchte-schnitzerei = The complete " +
                "manual to vegetable & fruit carving = Le grand manuel de la sculpture des " +
                "légumes & des fruit = Il grande manuale dell' intaglio di verdura & frutta / " +
                "Xiang Wang, artist."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Das grosse lehrbuch der gemüse- & früchte-schnitzerei"),
                        parallelTitles = listOf(
                            ParallelMonograph("The complete manual to vegetable & fruit carving"),
                            ParallelMonograph("Le grand manuel de la sculpture des légumes & des fruit"),
                            ParallelMonograph("Il grande manuale dell' intaglio di verdura & frutta")
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
    fun t9() {
        val title = "Russian stories = Russkie rasskazy / edited by Gleb Struve."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Russian stories"),
                        parallelTitles = listOf(ParallelMonograph("Russkie rasskazy"))
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
    fun t21() {
        val title = "Long nian dang an = Dragon year file / Ke Yunlu Zhu."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Long nian dang an"),
                        parallelTitles = listOf(ParallelMonograph("Dragon year file"))
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
    fun t23() {
        val title = "Hui dao can zhuo, hui dao sheng huo = Life / Cai Yingqing zhu."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Hui dao can zhuo, hui dao sheng huo"),
                        parallelTitles = listOf(ParallelMonograph("Life"))
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Spennigens land"),
                        parallelTitles = listOf(ParallelMonograph("The land of suspense"))
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
    fun t30() {
        val title = "A collection of Latin American folksongs = Collección de " +
                "cancionces folklóricas Latinoamericanas / [edited] by Raquel " +
                "González Paraíso and Francisco López."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("A collection of Latin American folksongs"),
                        parallelTitles = listOf(
                            ParallelMonograph("Collección de cancionces folklóricas Latinoamericanas")
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Opposites"),
                        parallelTitles = listOf(ParallelMonograph("Zheng fan, English-Chinese"))
                    )
                ),
                sors = listOf(SOR("[designed by Hakan Şan Borteçin]"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /************************************************
     *
     *  "Title proper / statement of responsibility
     *    = parallel statement of responsibility"
     */

    @Test
    fun t42() {
        val title = "Bibliotheca Celtica : a register of publications relating to " +
                "Wales and the Celtic peoples and languages / Llyfrgell Genedlaethol " +
                "Cymru = The National Library of Wales"

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Bibliotheca Celtica"),
                        otherInfo = listOf(
                            OtherInfo(
                                "a register of publications relating to " +
                                    "Wales and the Celtic peoples and languages"
                            )
                        )
                    )
                ),
                sors = listOf(SOR("Llyfrgell Genedlaethol Cymru")),
                parallelSORs = listOf(ParallelSOR("The National Library of Wales"))
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /**************************************************************
     *
     *  "Title proper / statement of responsibility
     *    = Parallel title / parallel statement of responsibility"
     */

    @Test
    fun t2() {
        val title = "Belgique [sound recording] : musique flamande = België : " +
                "Vlaamse volksmuziek = Belgium : Flemish folk music / " +
                "het Brabants Volksorkest \"Crispijn\"."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Belgique [sound recording]"),
                        otherInfo = listOf(OtherInfo("musique flamande")),
                        parallelTitles = listOf(
                            ParallelMonograph(
                                title = "België",
                                otherInfo = listOf(
                                    ParallelOtherInfo("Vlaamse volksmuziek")
                                )
                            ),
                            ParallelMonograph(
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
    fun t29() {
        val title = "Xiao xiao xiao xiao de huo / [Mei] Wu Qishi zhu" +
                " ; Sun Lu yi = Little fires everywhere / Celeste Ng."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Xiao xiao xiao xiao de huo")
                    )
                ),
                sors = listOf(
                    SOR("[Mei] Wu Qishi zhu"),
                    SOR("Sun Lu yi")
                ),
                parallelTitles = listOf(
                    ParallelMonograph(
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
    fun t17() {
        val title = "The missing chancleta and other top-secret cases / by Alidis Vincente" +
                " ; cover and inside illustrations by Leonardo Mora = La chancleta perdida y " +
                "ostros casos secretos / por Alidis Vincente ; traduccion al español de Gabriela " +
                "Baeza Ventura ; ilustraciones de Leonardo Mora."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The missing chancleta and other top-secret cases")
                    )
                ),
                sors = listOf(
                    SOR("by Alidis Vincente"),
                    SOR("cover and inside illustrations by Leonardo Mora")
                ),
                parallelTitles = listOf(
                    ParallelMonograph(
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

    /************************************************
     *
     *  "Title proper / statement of responsibility
     *    ; second statement of responsibility
     *    ; third statement of responsibility"
     */

    @Test
    fun t1() {
        val title = "Tous les matins du monde [videorecording] = " +
                "All the mornings of the world / Jean-Louis Livi présente" +
                " ; produit par Jean-Louis Livi ; un film de Alain Corneau."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Tous les matins du monde [videorecording]"),
                        parallelTitles = listOf(
                            ParallelMonograph("All the mornings of the world")
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
    fun t5() {
        val title = "The modern English-Nihongo dictionary = Nihongo gakushu Ei-Nichi jiten / " +
                "executive editor: Fumio Tamamura ; executive contributor: Kakuko Shoji."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The modern English-Nihongo dictionary"),
                        parallelTitles = listOf(
                            ParallelMonograph("Nihongo gakushu Ei-Nichi jiten")
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
    fun t13() {
        val title = "Wo he Huojin de sheng huo = Travelling to infinity / [Ying] " +
                "Jian·Huojing (Jane Hawking) zhu ; Zhang Jing, Wang Hanmin yi."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Wo he Huojin de sheng huo"),
                        parallelTitles = listOf(ParallelMonograph("Travelling to infinity"))
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
    fun t15() {
        val title = "Hands are not for hitting = Las manos no son para pegar / Martine " +
                "Agassi ; illustrado por Marieka Heinlen."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Hands are not for hitting"),
                        parallelTitles = listOf(ParallelMonograph("Las manos no son para pegar"))
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
            TitleStatement(
                titles = listOf(
                    Monograph(
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
    fun t19() {
        val title = "This can lick a lollipop : body riddles for kids = Esto gozo " +
                "chupando un caramelo : les partes del cuerpo en adivinanzas infantiles" +
                " / English words by Joel Rothman ; Spanish words by Argentica Palacios" +
                " ; photographs by Patricia Ruben."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("This can lick a lollipop"),
                        otherInfo = listOf(OtherInfo("body riddles for kids")),
                        parallelTitles = listOf(
                            ParallelMonograph(
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
    fun t25() {
        val title = "Atlas the gioi khu'ng long = The Usborne Internet-linked " +
                "world atlas of dinosaurs / Thiet ke: Susanna Davidson, Stephanie Turnbull, " +
                "va Rachel Firth ; Minh hoa: Todd Marshall, Barry Croucher, Nelupa " +
                "Hussain va Glen Bird ; Nguoi dich: Viet Hoang - Viet Chung."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Atlas the gioi khu'ng long"),
                        parallelTitles = listOf(
                            ParallelMonograph("The Usborne Internet-linked world atlas of dinosaurs")
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
    fun t28() {
        val title = "aal-Ghurāb al-miskīn = The Poor crow / ci'dād Sālim Shams al-Dīn" +
                " ; al-tarjamah ilá al-Inklīzīyah Shirīn al-Shammā', rusūm al-fannān " +
                "Nabīl Qaddūh."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("aal-Ghurāb al-miskīn"),
                        parallelTitles = listOf(ParallelMonograph("The Poor crow"))
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
    fun t32() {
        val title = "¿Qué hay en el cielo, querido dragón? = What's in the sky, dear dragon?" +
                " / por/by Margaret Hillert ; illustrado por/illustrated by David Schimmell" +
                " ; traducio por Queta Fernandez."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("¿Qué hay en el cielo, querido dragón?"),
                        parallelTitles = listOf(ParallelMonograph("What's in the sky, dear dragon?"))
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("¿Qué hay en el bosque, querido dragón?"),
                        parallelTitles = listOf(
                            ParallelMonograph("What's in the woods, dear dragon?")
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("How will we get to the beach?"),
                        parallelTitles = listOf(ParallelMonograph("Cómo iremos a la playa?"))
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("The world's most beautiful libraries"),
                        parallelTitles = listOf(
                            ParallelMonograph("Die schönsten Bibliotheken der Welt"),
                            ParallelMonograph("Les plus belles bibliothèques du monde")
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
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Mia madre"),
                        parallelTitles = listOf(ParallelMonograph("My mother"))
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
            TitleStatement(
                titles = listOf(
                    Monograph(
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
            TitleStatement(
                titles = listOf(
                    Monograph(
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

    @Test
    fun t39() {
        val title = "Under the green star / by Lin Carter ; illustrated by Tim Kirk" +
                " ; With an epilogue by the author on the \"Burroughs tradition\"."

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    Monograph(
                        titleProper = TitleProper("Under the green star")
                    )
                ),
                sors = listOf(
                    SOR("by Lin Carter"),
                    SOR("illustrated by Tim Kirk"),
                    SOR("With an epilogue by the author on the \"Burroughs tradition\"")
                )
            )
        )

        val result = t.parse(title)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}