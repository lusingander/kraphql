package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.baseTypeName
import com.github.lusingander.kraphql.graphql.isNonNull
import graphql.language.InputValueDefinition

class InputValue(
    val name: String,
    val type: String,
    val nonNull: Boolean
) {

    fun argsStr(): String = "$name: ${typeStr()}${defaultValueStr()}"
    fun pairStr(): String = "\"$name\" to $name"
    fun addArgStr(): String = ".apply { addArgs(\"$name\", $name) }"

    private fun typeStr(): String =
        if (nonNull) {
            type
        } else {
            "$type?"
        }

    private fun defaultValueStr(): String =
        if (nonNull) {
            ""
        } else {
            " = null"
        }
}

fun InputValueDefinition.convert(): InputValue {
    return InputValue(
        name = this.name,
        type = this.type.baseTypeName(),
        nonNull = this.type.isNonNull()
    )
}
