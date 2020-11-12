package com.github.lusingander.kraphql.graphql

enum class ScalarType(private val label: String) {
    ID("ID"),
    STRING("String"),
    INT("Int"),
    FLOAT("Float"),
    BOOLEAN("Boolean"),
    ;

    companion object {
        fun labels(): Set<String> = values().map { it.label }.toSet()
        fun stringTypeLabels(): Set<String> = setOf(ID, STRING).map { it.label }.toSet()
    }
}

