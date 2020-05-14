package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.NodeList
import ca.voidstarzero.isbd.titlestatement.ast.ParallelSOR
import ca.voidstarzero.isbd.titlestatement.ast.SOR
import norswap.autumn.DSL.rule

val TitleStatementGrammar.sor: rule
    get() = seq(slash, data)
        .push { items -> SOR(items[0] as String) }

val TitleStatementGrammar.additionalSOR: rule
    get() = seq(semicolon, data).at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { SOR(it as String) })
        }

val TitleStatementGrammar.sorList: rule
    get() = seq(sor, additionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as SOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }

val TitleStatementGrammar.parallelSOR: rule
    get() = seq(slash, data)
        .push { items ->
            ParallelSOR(items[0] as String)
        }

val TitleStatementGrammar.parallelAdditionalSOR: rule
    get() = seq(semicolon, data).at_least(1)
        .push { items ->
            NodeList(items.filterNotNull().map { ParallelSOR(it as String) })
        }

val TitleStatementGrammar.parallelSORList: rule
    get() = seq(parallelSOR, parallelAdditionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as ParallelSOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }