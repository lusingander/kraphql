package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.RootType
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
