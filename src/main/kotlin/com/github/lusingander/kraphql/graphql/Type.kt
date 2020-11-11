package com.github.lusingander.kraphql.graphql

import graphql.language.ListType
import graphql.language.NonNullType
import graphql.language.Type
import graphql.language.TypeName

fun Type<*>.baseTypeName(): String {
    return when (this) {
        is NonNullType -> this.type.baseTypeName()
        is ListType -> this.type.baseTypeName()
        is TypeName -> this.name
        else -> throw Exception("Unexpected type: $this")
    }
}

fun Type<*>.isNonNull(): Boolean = this is NonNullType
