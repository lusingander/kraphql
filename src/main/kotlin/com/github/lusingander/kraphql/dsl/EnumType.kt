package com.github.lusingander.kraphql.dsl

import graphql.language.EnumTypeDefinition
import java.io.PrintWriter

class EnumType(
    val name: String,
    val values: List<String>
) {

    fun build(writer: PrintWriter) {
        writer.println("enum class $name {")
        values.forEach {
            writer.println("    $it,")
        }
        writer.println("    ;")
        writer.println("}")
    }
}

fun EnumTypeDefinition.convert(): EnumType {
    return EnumType(
        name = this.name,
        values = this.enumValueDefinitions.map { it.name }
    )
}
