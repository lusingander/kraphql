package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.baseTypeName
import com.github.lusingander.kraphql.graphql.isNonNull
import com.github.lusingander.kraphql.kotlin.backquote
import graphql.language.InputValueDefinition

class InputValue(
    val name: String,
    val qname: String,
    val type: String,
    val nonNull: Boolean
) {

    fun argsStr(): String = "$qname: ${typeStr()}${defaultValueStr()}"
    fun pairStr(): String = "\"$name\" to $qname"
    fun addArgStr(): String = ".apply { addArgs(\"$name\", $qname) }"

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
        qname = this.name.backquote(),
        type = this.type.baseTypeName(),
        nonNull = this.type.isNonNull()
    )
}
