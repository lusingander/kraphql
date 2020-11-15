package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.kotlin.backquote
import graphql.language.InputObjectTypeDefinition
import java.io.PrintWriter

class InputObjectType(
    val name: String,
    val qname: String,
    val inputs: List<InputValue>
) {

    fun build(writer: PrintWriter, stringTypeScalars: Set<String>) {
        val args = inputs.joinToString(", ") { "val ${it.argsStr()}" }
        val queryArgs = inputs.joinToString(", ") { queryArgsStr(it, stringTypeScalars) }
        writer.println("")
        writer.println("class $qname($args) {")
        writer.println("    override fun toString() = \"{ $queryArgs }\"")
        writer.println("}")
    }

    private fun queryArgsStr(input: InputValue, stringTypeScalars: Set<String>): String {
        val name = input.name
        val value = if (input.type in stringTypeScalars)"\\\"$$name\\\"" else "$$name"
        return "$name: $value"
    }
}

fun InputObjectTypeDefinition.convert(): InputObjectType {
    return InputObjectType(
        name = this.name,
        qname = this.name.backquote(),
        inputs = this.inputValueDefinitions.map { it.convert() }
    )
}
