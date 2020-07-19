package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.NodeList
import ca.voidstarzero.isbd.titlestatement.ast.ParallelSOR
import ca.voidstarzero.isbd.titlestatement.ast.SOR
import norswap.autumn.DSL.rule

val TitleStatementGrammar.sor: rule
    get() = sorOfType(::SOR)

val TitleStatementGrammar.additionalSOR: rule
    get() = additionalSOROfType(::SOR)

val TitleStatementGrammar.sorList: rule
    get() = seq(sor, additionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as SOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }

val TitleStatementGrammar.parallelSOR: rule
    get() = sorOfType(::ParallelSOR)

val TitleStatementGrammar.parallelAdditionalSOR: rule
    get() = additionalSOROfType(::ParallelSOR)

val TitleStatementGrammar.parallelSORList: rule
    get() = seq(parallelSOR, parallelAdditionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as ParallelSOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }