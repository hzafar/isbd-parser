package ca.voidstarzero.isbd.titlestatement.grammar

import ca.voidstarzero.isbd.titlestatement.ast.NodeList
import ca.voidstarzero.isbd.titlestatement.ast.ParallelSOR
import ca.voidstarzero.isbd.titlestatement.ast.SOR
import norswap.autumn.DSL.rule

/**
 * Matches a [TitleStatementGrammar.slash] followed by a string of characters.
 *
 * Pushes the matched data as a [SOR] to the parser's value stack.
 */
val TitleStatementGrammar.sor: rule
    get() = sorOfType(::SOR)

/**
 * Matches a sequence of [TitleStatementGrammar.data] separated by [TitleStatementGrammar.semicolon].
 *
 * Pushes the matched data as a [NodeList] of [SOR] to the parser's value stack.
 */
val TitleStatementGrammar.additionalSOR: rule
    get() = additionalSOROfType(::SOR)

/**
 * Matches a list of statements of responsibility.
 *
 * Pushes the result as a [NodeList] of [SOR] to the parser's value stack.
 */
val TitleStatementGrammar.sorList: rule
    get() = seq(sor, additionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as SOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }

/**
 * Matches a [TitleStatementGrammar.slash] followed by a string of characters.
 *
 * Pushes the matched data as a [ParallelSOR] to the parser's value stack.
 */
val TitleStatementGrammar.parallelSOR: rule
    get() = sorOfType(::ParallelSOR)

/**
 * Matches a sequence of [TitleStatementGrammar.data] separated by [TitleStatementGrammar.semicolon].
 *
 * Pushes the matched data as a [NodeList] of [ParallelSOR] to the parser's value stack.
 */
val TitleStatementGrammar.parallelAdditionalSOR: rule
    get() = additionalSOROfType(::ParallelSOR)

/**
 * Matches a list of statements of responsibility.
 *
 * Pushes the result as a [NodeList] of [ParallelSOR] to the parser's value stack.
 */
val TitleStatementGrammar.parallelSORList: rule
    get() = seq(parallelSOR, parallelAdditionalSOR.maybe())
        .push { items ->
            NodeList(
                listOf(items[0] as ParallelSOR)
                    .plus((items[1] as? NodeList)?.values ?: emptyList())
            )
        }