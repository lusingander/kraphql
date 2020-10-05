package com.github.lusingander.kraphql.graphql

enum class RootType(val label: String) {
    QUERY("Query"),
    MUTATION("Mutation"),
    SUBSCRIPTION("Subscription"),
    ;

    companion object {
        fun labels(): Set<String> = RootType.values().map { it.label }.toSet()
    }
}