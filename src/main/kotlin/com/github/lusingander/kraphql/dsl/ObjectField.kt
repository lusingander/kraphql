package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.baseTypeName
import graphql.language.FieldDefinition
import java.io.PrintWriter

class ObjectField(
    val name: String,
    val baseType: String,
    val inputs: List<InputValue>
) {

    fun build(writer: PrintWriter, scalars: Set<String>) {
        if (isScalar(scalars)) {
            if (hasInputs()) {
                val args = inputs.joinToString(separator = ", ") { it.argsStr() }
                val pairs = inputs.joinToString(separator = ", ") { it.pairStr() }
                writer.println("    fun $name($args) =")
                writer.println("        ScalarWithArgsNode(\"$name\", mapOf($pairs)).also { doInit(it) }")
                if (inputs.none { it.nonNull }) {
                    writer.println("    val $name get() =")
                    writer.println("        ScalarNode(\"$name\").also { doInit(it) }")
                }
            } else {
                writer.println("    val $name get() =")
                writer.println("        ScalarNode(\"$name\").also { doInit(it) }")
            }
        } else {
            if (hasInputs()) {
                val args = inputs.joinToString(separator = ", ") { it.argsStr() }
                val addArgs = inputs.joinToString(separator = "") { it.addArgStr() }
                writer.println("    fun $name(${args}, init: $baseType.() -> Unit) =")
                writer.println("        $baseType(\"$name\")$addArgs.also { doInit(it, init) }")
            } else {
                writer.println("    fun $name(init: $baseType.() -> Unit) =")
                writer.println("        $baseType(\"$name\").also { doInit(it, init) }")
            }
        }
    }

    private fun isScalar(scalars: Set<String>): Boolean =
        baseType in scalars

    private fun hasInputs(): Boolean = inputs.isNotEmpty()
}

fun FieldDefinition.convert(): ObjectField {
    return ObjectField(
        name = this.name,
        baseType = this.type.baseTypeName(),
        inputs = this.inputValueDefinitions.map { it.convert() }
    )
}
