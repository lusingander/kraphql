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
    }
}

