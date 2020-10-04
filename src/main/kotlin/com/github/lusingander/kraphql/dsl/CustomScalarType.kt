package com.github.lusingander.kraphql.dsl

import graphql.language.ScalarTypeDefinition

class CustomScalarType(
    val name: String
)

fun ScalarTypeDefinition.convert(): CustomScalarType {
    return CustomScalarType(
        name = this.name
    )
}