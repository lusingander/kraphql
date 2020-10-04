package com.github.lusingander.kraphql.graphql

enum class RootType(val label: String) {
    QUERY("Query"),
    MUTATION("Mutation"),
    SUBSCRIPTION("Subscription"),
    ;
}