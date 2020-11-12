package com.github.lusingander.kraphql.graphql

import graphql.language.Directive
import graphql.language.StringValue

enum class Directives(val label: String) {
    DEPRECATED("deprecated"),
    ;
}

fun Directive.isDeprecated() = this.name == Directives.DEPRECATED.label

fun Directive.getReason(): String? {
    val argument = this.getArgument("reason")
    val value = argument.value as? StringValue
    return value?.value
}