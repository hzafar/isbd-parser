package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.titlestatement.ast.*
import ca.voidstarzero.marc.MARCField
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Ignore
import org.junit.Test

class SeriesTitleTest {

    private val t = TitleStatementParser()

    /***********************************
     *
     * "Common title. Dependent title"
     */

    @Test
    fun t1() {
        val title = "Abstracts of Bulgarian scientific literature. Mathematics, physics, " +
                "astronomy, geophysics, geodesy / Bulgarian Academy of Sciences, Centre " +
                "for Scientific Information and Documentation."
        val marc = MARCField(
            "245",
            "|aAbstracts of Bulgarian scientific literature.|pMathematics, physics, " +
                    "astronomy, geophysics, geodesy /|cBulgarian Academy of Sciences, Centre " +
                    "for Scientific Information and Documentation.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Abstracts of Bulgarian scientific literature"),
                        entryTitle = SeriesEntryTitle("Mathematics, physics, astronomy, geophysics, geodesy")
                    )
                ),
                sors = listOf(
                    SOR("Bulgarian Academy of Sciences, Centre for Scientific Information " +
                            "and Documentation")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /****************************************************************
     *
     * "Common title. Dependent title designation, Dependent title"
     */

    @Test
    fun t2() {
        val title = "Nauchno-tekhnicheskai︠a︡ informat︠s︡ii︠a︡. Serii︠a︡ 2, Informat︠s︡ionnye prot︠s︡essy i sistemy" +
                " / Vsesoi︠u︡znyĭ institut nauchnoĭ i tekhnicheskoĭ informat︠s︡ii."
        val marc = MARCField(
            "245",
            "|6880-01|aNauchno-tekhnicheskai︠a︡ informat︠s︡ii︠a︡.|nSerii︠a︡ 2,|pInformat︠s︡ionnye " +
                    "prot︠s︡essy i sistemy /|cVsesoi︠u︡znyĭ institut nauchnoĭ i tekhnicheskoĭ informat︠s︡ii.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Nauchno-tekhnicheskai︠a︡ informat︠s︡ii︠a︡"),
                        designation = SeriesEntryDesignation("Serii︠a︡ 2"),
                        entryTitle = SeriesEntryTitle("Informat︠s︡ionnye prot︠s︡essy i sistemy")
                    )
                ),
                sors = listOf(
                    SOR("Vsesoi︠u︡znyĭ institut nauchnoĭ i tekhnicheskoĭ informat︠s︡ii")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t3() {
        val title = "Vestnik Leningradskogo universiteta. Serii︠a︡ 3, Biologii︠a︡."
        val marc = MARCField(
            "245",
            "|aVestnik Leningradskogo universiteta.|nSerii︠a︡ 3,|pBiologii︠a︡.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Vestnik Leningradskogo universiteta"),
                        designation = SeriesEntryDesignation("Serii︠a︡ 3"),
                        entryTitle = SeriesEntryTitle("Biologii︠a︡")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t4() {
        val title = "Bulletin of the Faculty of Science, Ibaraki University. Series A, Mathematics."
        val marc = MARCField(
            "245",
            "|aBulletin of the Faculty of Science, Ibaraki University." +
                    "|nSeries A,|pMathematics.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Bulletin of the Faculty of Science, Ibaraki University"),
                        designation = SeriesEntryDesignation("Series A"),
                        entryTitle = SeriesEntryTitle("Mathematics")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t5() {
        val title = "MInd, the meetings index. Series SEMT, Science, engineering, medicine, technology."
        val marc = MARCField(
            "245",
            "|aMInd, the meetings index.|nSeries SEMT,|pScience, engineering, " +
                    "medicine, technology.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("MInd, the meetings index"),
                        designation = SeriesEntryDesignation("Series SEMT"),
                        entryTitle = SeriesEntryTitle("Science, engineering, medicine, technology")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t6() {
        val title = "Bulletin of the Stefan University. Series B, Stefan frontier conferences."
        val marc = MARCField(
            "245",
            "|aBulletin of the Stefan University.|nSeries B,|pStefan frontier conferences.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Bulletin of the Stefan University"),
                        designation = SeriesEntryDesignation("Series B"),
                        entryTitle = SeriesEntryTitle("Stefan frontier conferences")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /***********************************************
     *
     * "Common title. Dependent title designation"
     */

    @Test
    fun t7() {
        val title = "Los 10 mejores videoss. Vol. 2."
        val marc = MARCField(
            "245",
            "|aLos 10 mejores videoss.|nVol. 2.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Los 10 mejores videoss"),
                        designation = SeriesEntryDesignation("Vol. 2")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t8() {
        val title = "Chinese annals of mathematics. Ser. B."
        val marc = MARCField(
            "245",
            "|aChinese annals of mathematics.|nSer. B.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Chinese annals of mathematics"),
                        designation = SeriesEntryDesignation("Ser. B")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }


    /****************************************************
     *
     * "Common title. Dependent title
     *   = Parallel common title. Parallel dependent title"
     */

    @Test
    fun t9() {
        val title = "Izvestii︠a︡ Akademii nauk SSSR. Otdelenie khimicheskikh nauk" +
                " = Bulletin de l'Académie des sciences de l'URSS. Classe des sciences chimiques."
        val marc = MARCField(
            "245",
            "|6880-01|aIzvestii︠a︡ Akademii nauk SSSR.|pOtdelenie khimicheskikh nauk =" +
                    "|bBulletin de l'Académie des sciences de l'URSS. Classe des sciences chimiques.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Izvestii︠a︡ Akademii nauk SSSR"),
                        entryTitle = SeriesEntryTitle("Otdelenie khimicheskikh nauk")
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeriesEntry(
                        seriesTitle = SeriesTitle("Bulletin de l'Académie des sciences de l'URSS"),
                        entryTitle = SeriesEntryTitle("Classe des sciences chimiques")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t10() {
        val title = "Agricultural prices. Price indices and absolute prices, quarterly statistics" +
                " = Prix agricoles. Indices de prix et prix absolus, statistiques trimestrielles."
        val marc = MARCField(
            "245",
            "|aAgricultural prices.|pPrice indices and absolute prices, quarterly statistics =" +
                    "|bPrix agricoles. Indices de prix et prix absolus, statistiques trimestrielles.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Agricultural prices"),
                        entryTitle = SeriesEntryTitle("Price indices and absolute prices, quarterly statistics")
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeriesEntry(
                        seriesTitle = SeriesTitle("Prix agricoles"),
                        entryTitle = SeriesEntryTitle("Indices de prix et prix absolus, statistiques trimestrielles")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t11() {
        val title = "Bulletin of the Russian Academy of Sciences. Physics = Izvestiya Rossiiskoi " +
                "Akademii Nauk. Seriya fizicheskaya."
        val marc = MARCField(
            "245",
            "|aBulletin of the Russian Academy of Sciences.|pPhysics =|bIzvestiya " +
                    "Rossiiskoi Akademii Nauk. Seriya fizicheskaya.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Bulletin of the Russian Academy of Sciences"),
                        entryTitle = SeriesEntryTitle("Physics")
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeriesEntry(
                        seriesTitle = SeriesTitle("Izvestiya Rossiiskoi Akademii Nauk"),
                        entryTitle = SeriesEntryTitle("Seriya fizicheskaya")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    @Ignore("Designations in parallel titles not correctly recognized.")
    fun t12() {
        val title = "Befolkningsstatistikk. Hefte I, Endringstal for kommunar = Population statistics. " +
                "Volume I, Population changes in municipalities."
        val marc = MARCField(
            "245",
            "|aBefolkningsstatistikk.|nHefte I,|pEndringstal for kommunar =" +
                    "|bPopulation statistics. Volume I, Population changes in municipalities.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Befolkningsstatistikk"),
                        designation = SeriesEntryDesignation("Hefte I"),
                        entryTitle = SeriesEntryTitle("Endringstal for kommunar")
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeriesEntry(
                        seriesTitle = SeriesTitle("Population statistics"),
                        designation = SeriesEntryDesignation("Volume I"),
                        entryTitle = SeriesEntryTitle("Population changes in municipalities")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /*****************************************************************
     *
     * "Common title. Dependent title / statement of responsibility"
     */

    @Test
    fun t13() {
        val title = "The royals. The slings and arrows of outrageous fortune / E! Entertainment Television."

        val marc = MARCField(
            "245",
            "|aThe royals.|pThe slings and arrows of outrageous fortune /" +
                    "|cE! Entertainment Television.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("The royals"),
                        entryTitle = SeriesEntryTitle("The slings and arrows of outrageous fortune")
                    )
                ),
                sors = listOf(
                    SOR("E! Entertainment Television")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t14() {
        val title = "Americas. The garden of forking paths / a production of WGBH/Boston and " +
                "Central Television Enterprises for Channel 4 ; directed and written by David Ash."
        val marc = MARCField(
            "245",
            "|aAmericas.|pThe garden of forking paths /|ca production of " +
                    "WGBH/Boston and Central Television Enterprises for Channel 4 ; directed " +
                    "and written by David Ash.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Americas"),
                        entryTitle = SeriesEntryTitle("The garden of forking paths")
                    )
                ),
                sors = listOf(
                    SOR("a production of WGBH/Boston and Central Television Enterprises for Channel 4"),
                    SOR("directed and written by David Ash")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t15() {
        val title = "Los años maravillosos. Amor y muerte / BeTV."
        val marc = MARCField(
            "245",
            "|aLos años maravillosos.|pAmor y muerte /|cBeTV.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Los años maravillosos"),
                        entryTitle = SeriesEntryTitle("Amor y muerte")
                    )
                ),
                sors = listOf(
                    SOR("BeTV")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t16() {
        val title = "Prófugos. Episode 13 / Efetres ; Fabula ; Home Box Office Ole Originals."
        val marc = MARCField(
            "245",
            "|aPrófugos.|nEpisode 13 /|cEfetres ; Fabula ; Home Box Office Ole Originals.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Prófugos"),
                        designation = SeriesEntryDesignation("Episode 13")
                    )
                ),
                sors = listOf(
                    SOR("Efetres"),
                    SOR("Fabula"),
                    SOR("Home Box Office Ole Originals")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t17() {
        val title = "Annuario de estadísticas vitales. Nacimientos y defunciones / Instituto Nacional " +
                "de Estadística y Censos."
        val marc = MARCField(
            "245",
            "|aAnuario de estadísticas vitales.|pNacimientos y defunciones /" +
                    "|cInstituto Nacional de Estadística y Censos.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Annuario de estadísticas vitales"),
                        entryTitle = SeriesEntryTitle("Nacimientos y defunciones")
                    )
                ),
                sors = listOf(
                    SOR("Instituto Nacional de Estadística y Censos")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /************************************************
     *
     * "Common title : other title information.
     *   Dependent title : other title information"
     */

    @Test
    fun t18() {
        val title = "Acta polytechnica Scandinavica. Chemical technology series : ch."
        val marc = MARCField(
            "245",
            "|aActa polytechnica Scandinavica.|pChemical technology series :|bch.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Acta polytechnica Scandinavica"),
                        entryTitle = SeriesEntryTitle(
                            title = "Chemical technology series",
                            otherInfo = listOf(SeriesEntryOtherInfo("ch"))
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    @Ignore("Periods in entry title are not handled correctly.")
    fun t19() {
        val title = "Internationale Strassenkarte. Europe 1:2.5 Mio. : mit Register" +
                " = International road map. Europe, 1:2.5 mio : with index / RV Reise- " +
                "und Verkehrsverlag."
        val marc = MARCField(
            "245",
            "|aInternationale Strassenkarte.|pEurope 1:2.5 Mio. :|bmit Register" +
                    " = International road map.|pEurope, 1:2.5 mio : with index /|cRV Reise- " +
                    "und Verkehrsverlag.",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Internationale Strassenkarte"),
                        entryTitle = SeriesEntryTitle(
                            title = "Europe 1:2.5 Mio",
                            otherInfo = listOf(SeriesEntryOtherInfo("mit Register"))
                        )
                    )
                ),
                parallelTitles = listOf(
                    ParallelSeriesEntry(
                        seriesTitle = SeriesTitle("International road map"),
                        entryTitle = SeriesEntryTitle(
                            title = "Europe, 1:2.5 mio",
                            otherInfo = listOf(SeriesEntryOtherInfo("with index"))
                        )
                    )
                ),
                sors = listOf(
                    SOR("RV Reise- und Verkehrsverlag")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t20() {
        val title = "Bibliographie de la France Biblio : journal officiel du livre francais " +
                "paraissant tous les mercredis. 1ere partie, Bibliographie officielle : " +
                "publications recues par le Service du depot legl"
        val marc = MARCField(
            "245",
            "|aBibliographie de la France Biblio :|bjournal officiel du livre francais " +
                    "paraissant tous les mercredis.|n1ere partie,|pBibliographie officielle : " +
                    "publications recues par le Service du depot legl",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle(
                            title = "Bibliographie de la France Biblio",
                            otherInfo = listOf(
                                SeriesOtherInfo("journal officiel du livre francais " +
                                        "paraissant tous les mercredis")
                            )
                        ),
                        designation = SeriesEntryDesignation("1ere partie"),
                        entryTitle = SeriesEntryTitle(
                            title = "Bibliographie officielle",
                            otherInfo = listOf(
                                SeriesEntryOtherInfo("publications recues par " +
                                    "le Service du depot legl")
                            )
                        )
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    fun t21() {
        val title = "Periodica polytechnica : contributions to international technical sciences " +
                "published by the Technical University of Budapest. Transportation engineering"
        val marc = MARCField(
            "245",
            "|aPeriodica polytechnica :|bcontributions to international technical " +
                    "sciences published by the Technical University of Budapest.|pTransportation " +
                    "engineering",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle(
                            title = "Periodica polytechnica",
                            otherInfo = listOf(
                                SeriesOtherInfo("contributions to international technical" +
                                        " sciences published by the Technical University of Budapest")
                            )
                        ),
                        entryTitle = SeriesEntryTitle("Transportation engineering")
                    )
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    /*****************************************************************
     *
     * "Common title. Dependent title : other title information
     *   / statement of responsibility = Parallel common title.
     *   Parallel dependent title : parallel other title information
     *   / parallel statement of responsibility"
     */

    // TODO: add tests for this case

    /*******************
     * Hierarchical entries.
     */

    @Test
    @Ignore("Hierarchical entries.")
    fun t22() {
        val title = "Publications of the Institute. Historical Studies. Series III, Renaissance " +
                "Europe : texts and commentaries"
        val marc = MARCField(
            "245",
            "|aPublications of the Institute.|pHistorical Studies.|nSeries III," +
                    "|pRenaissance Europe : texts and commentaries",
            '|'
        )

        val expected = TODO()

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    @Ignore("Hierarchical entries.")
    fun t23() {
        val title = "Deutsche Bibliographie. Wöchentliches Verzeichnis. Reihe B, Beilage, " +
                "Erscheinungen ausserhalb des Verlagsbuchhandels : Amtsblatt der Deutschen Bibliothek."
        val marc = MARCField(
            "245",
            "|aDeutsche Bibliographie.|pWöchentliches Verzeichnis.|nReihe B," +
                    "|pBeilage, Erscheinungen ausserhalb des Verlagsbuchhandels :|bAmtsblatt " +
                    "der Deutschen Bibliothek.",
            '|'
        )

        val expected = TODO()

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }

    @Test
    @Ignore("Hierarchical entries.")
    fun t24() {
        val title = "Australian Council for Educational Research. Series 3. Memorandum. Hawthorn, " +
                "Australia. 1966- ."
        val marc = MARCField(
            "245",
            "|aAustralian Council for Educational Research. Series 3. Memorandum. " +
                    "Hawthorn, Australia. 1966- .",
            '|'
        )

        val expected = listOf(
            TitleStatement(
                titles = listOf(
                    SeriesEntry(
                        seriesTitle = SeriesTitle("Nauchno-tekhnicheskai︠a︡ informat︠s︡ii︠"),
                        designation = SeriesEntryDesignation("Serii︠a︡ 2"),
                        entryTitle = SeriesEntryTitle("Informat︠s︡ionnye prot︠s︡essy i sistemy")
                    )
                ),
                sors = listOf(
                    SOR("Vsesoi︠u︡znyĭ institut nauchnoĭ i tekhnicheskoĭ informat︠s︡ii")
                )
            )
        )

        val result = t.parseSerial(title, marc)

        assertNotNull(result)
        assertEquals(expected, result)
    }
}