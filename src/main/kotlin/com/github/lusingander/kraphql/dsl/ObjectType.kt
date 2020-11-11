package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.RootType
import com.sun.jdi.InterfaceType
import graphql.language.*
import java.io.PrintWriter

class ObjectType(
    val name: String,
    val fields: List<ObjectField>
) {

    fun build(writer: PrintWriter, customScalars: Set<String>) {
        writer.println("")
        writer.println("class $name(__name: String = \"${objectName()}\"): ObjectNode(__name) {")
        fields.forEach { field ->
            field.build(writer, customScalars)
        }
        writer.println("}")
    }

    private fun objectName(): String =
        if (name in RootType.labels()) name.toLowerCase() else name
}

fun ObjectTypeDefinition.convert(): ObjectType {
    return ObjectType(
        name = this.name,
        fields = this.fieldDefinitions.map { it.convert() }
    )
}

fun InterfaceTypeDefinition.convert(): ObjectType {
    return ObjectType(
        name = this.name,
        fields = this.fieldDefinitions.map { it.convert() }
    )
}

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

fun Type<*>.baseTypeName(): String {
    return when (this) {
        is NonNullType -> this.type.baseTypeName()
        is ListType -> this.type.baseTypeName()
        is TypeName -> this.name
        else -> throw Exception("Unexpected type: $this")
    }
}

fun Type<*>.isNonNull(): Boolean = this is NonNullType

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