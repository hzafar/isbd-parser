package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.marc.MARCField
import org.junit.Test

class SeriesTest {

    private val t = TitleStatementParser()

    @Test
    fun t1() {
        val title = "Los 10 mejores videoss. Vol. 2."
        val marc = MARCField(
            "245",
            "|aLos 10 mejores videoss.|n Vol. 2.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t2() {
        val title = "Americas. The garden of forking paths / a production of WGBH/Boston and " +
                "Central Television Enterprises for Channel 4 ; directed and written by David Ash."
        val marc = MARCField(
            "245",
            "|aAmericas.|pThe garden of forking paths /|ca production of " +
                    "WGBH/Boston and Central Television Enterprises for Channel 4 ; directed " +
                    "and written by David Ash.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t3() {
        val title = "Los años maravillosos. Amor y muerte / BeTV."
        val marc = MARCField(
            "245",
            "|aLos años maravillosos.|pAmor y muerte /|cBeTV.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t4() {
        val title = "Prófugos. Episode 13 / Efetres ; Fabula ; Home Box Office Ole Originals."
        val marc = MARCField(
            "245",
            "|aPrófugos.|n Episode 13 /|c Efetres ; Fabula" +
                    " ; Home Box Office Ole Originals.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t5() {
        val title = "Annuario de estadísticas vitales. Nacimientos y defunciones / Instituto Nacional " +
                "de Estadística y Censos."
        val marc = MARCField(
            "245",
            "|aAnuario de estadísticas vitales.|pNacimientos y defunciones /" +
                    "|cInstituto Nacional de Estadística y Censos.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t6() {
        val title = "Izvestii︠a︡ Akademii nauk SSSR. Otdelenie khimicheskikh nauk" +
                " = Bulletin de l'Académie des sciences de l'URSS. Classe des sciences chimiques."
        val marc = MARCField(
            "245",
            "|6880-01|aIzvestii︠a︡ Akademii nauk SSSR.|pOtdelenie khimicheskikh nauk =" +
                    "|bBulletin de l'Académie des sciences de l'URSS. Classe des sciences chimiques.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t7() {
        val title = "Nauchno-tekhnicheskai︠a︡ informat︠s︡ii︠a︡. Serii︠a︡ 2, Informat︠s︡ionnye prot︠s︡essy i sistemy" +
                " / Vsesoi︠u︡znyĭ institut nauchnoĭ i tekhnicheskoĭ informat︠s︡ii."
        val marc = MARCField(
            "245",
            "|6880-01|aNauchno-tekhnicheskai︠a︡ informat︠s︡ii︠a︡.|nSerii︠a︡ 2,|pInformat︠s︡ionnye " +
                    "prot︠s︡essy i sistemy /|cVsesoi︠u︡znyĭ institut nauchnoĭ i tekhnicheskoĭ informat︠s︡ii.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t8() {
        val title = "Vestnik Leningradskogo universiteta. Serii︠a︡ 3, Biologii︠a︡."
        val marc = MARCField(
            "245",
            "|aVestnik Leningradskogo universiteta.|nSerii︠a︡ 3,|pBiologii︠a︡.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t9() {
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

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t10() {
        val title = "Chinese annals of mathematics. Ser. B."
        val marc = MARCField(
            "245",
            "|aChinese annals of mathematics.|nSer. B.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t11() {
        val title = "Bulletin of the Faculty of Science, Ibaraki University. Series A, Mathematics."
        val marc = MARCField(
            "245",
            "|aBulletin of the Faculty of Science, Ibaraki University." +
                    "|nSeries A,|pMathematics.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t12() {
        val title = "MInd, the meetings index. Series SEMT, Science, engineering, medicine, technology."
        val marc = MARCField(
            "245",
            "|aMInd, the meetings index.|nSeries SEMT,|pScience, engineering, " +
                    "medicine, technology.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t13() {
        val title = "Acta polytechnica Scandinavica. Chemical technology series : ch."
        val marc = MARCField(
            "245",
            "|aActa polytechnica Scandinavica.|pChemical technology series :|bch.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t14() {
        val title = "Publications of the Institute. Historical Studies. Series III, Renaissance " +
                "Europe : texts and commentaries"
        val marc = MARCField(
            "245",
            "|aPublications of the Institute.|pHistorical Studies.|nSeries III," +
                    "|pRenaissance Europe : texts and commentaries",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t15() {
        val title = "Agricultural prices. Price indices and absolute prices, quarterly statistics" +
                " = Prix agricoles. Indices de prix et prix absolus, statistiques trimestrielles."
        val marc = MARCField(
            "245",
            "|aAgricultural prices.|pPrice indices and absolute prices, quarterly statistics =" +
                    "|bPrix agricoles. Indices de prix et prix absolus, statistiques trimestrielles.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t16() {
        val title = "Australian Council for Educational Research. Series 3. Memorandum. Hawthorn, " +
                "Australia. 1966- ."
        val marc = MARCField(
            "245",
            "|aAustralian Council for Educational Research. Series 3. Memorandum. " +
                    "Hawthorn, Australia. 1966- .",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t17() {
        val title = "Bulletin of the Stefan University. Series B, Stefan frontier conferences."
        val marc = MARCField(
            "245",
            "|aBulletin of the Stefan University.|nSeries B,|pStefan frontier conferences.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t18() {
        val title = "Bulletin of the Russian Academy of Sciences. Physics = Izvestiya Rossiiskoi " +
                "Akademii Nauk. Seriya fizicheskaya."
        val marc = MARCField(
            "245",
            "|aBulletin of the Russian Academy of Sciences.|pPhysics =|bIzvestiya " +
                    "Rossiiskoi Akademii Nauk. Seriya fizicheskaya.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t19() {
        val title = "Befolkningsstatistikk. Hefte I, Endringstal for kommunar = Population statistics. " +
                "Volume I, Population changes in municipalities."
        val marc = MARCField(
            "245",
            "|aBefolkningsstatistikk.|nHefte I,|pEndringstal for kommunar =" +
                    "|bPopulation statistics. Volume I, Population changes in municipalities.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t20() {
        val title = "Deutsche Bibliographie. Wöchentliches Verzeichnis. Reihe B, Beilage, " +
                "Erscheinungen ausserhalb des Verlagsbuchhandels : Amtsblatt der Deutschen Bibliothek."
        val marc = MARCField(
            "245",
            "|aDeutsche Bibliographie.|pWöchentliches Verzeichnis.|nReihe B," +
                    "|pBeilage, Erscheinungen ausserhalb des Verlagsbuchhandels :|bAmtsblatt " +
                    "der Deutschen Bibliothek.",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t21() {
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

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t22() {
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

        val result = t.parseSerial(title)

        println(result)
    }

    @Test
    fun t23() {
        val title = "Periodica polytechnica : contributions to international technical sciences " +
                "published by the Technical University of Budapest. Transportation engineering"
        val marc = MARCField(
            "245",
            "|aPeriodica polytechnica :|bcontributions to international technical " +
                    "sciences published by the Technical University of Budapest.|pTransportation " +
                    "engineering",
            '|'
        )

        val result = t.parseSerial(title)

        println(result)
    }
}