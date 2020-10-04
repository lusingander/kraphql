package com.github.lusingander.kraphql.dsl

import graphql.language.ScalarTypeDefinition

enum class ScalarTypes(private val label: String) {
    ID("ID"),
    STRING("String"),
    INT("Int"),
    FLOAT("Float"),
    BOOLEAN("Boolean"),
    ;

    companion object {
        fun labels(): Set<String> = ScalarTypes.values().map { it.label }.toSet()
    }
}

class CustomScalarType(
    val name: String
)

fun ScalarTypeDefinition.convert(): CustomScalarType {
    return CustomScalarType(
        name = this.name
    )
}