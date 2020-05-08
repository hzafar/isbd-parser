package ca.voidstarzero.isbd.titlestatement

import ca.voidstarzero.isbd.prepare
import ca.voidstarzero.isbd.titlestatement.ast.*
import norswap.autumn.Autumn
import norswap.autumn.ParseOptions

class TitleStatement : TitleStatementGrammar() {
    
    fun parse(input: String): List<TitleStatementNode> {
        return prepare(input)
            .map { Autumn.parse(root, it, ParseOptions.get()) }
            .firstOrNull { it.full_match }
            ?.let { result ->
                result.value_stack.mapNotNull { it as TitleStatementNode }
            }
            ?: emptyList()
    }

    fun parseHeuristically(input: String): List<TitleStatementNode> {
        return parseAll(input).firstOrNull { goodParse(it) } ?: emptyList()
    }

    fun parseAll(input: String): List<List<TitleStatementNode>> {
        return prepare(input)
            .map { Autumn.parse(root, it, ParseOptions.get()) }
            .filter { it.full_match }
            .map { result ->
                result.value_stack.mapNotNull { it as TitleStatementNode }
            }
    }
}