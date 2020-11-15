package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.RootType
import com.github.lusingander.kraphql.graphql.baseTypeName
import com.github.lusingander.kraphql.kotlin.backquote
import graphql.language.*
import java.io.PrintWriter

class ObjectType(
    val name: String,
    val qname: String,
    val fields: List<ObjectField>,
    val implements: List<String>
) {

    fun build(writer: PrintWriter, customScalars: Set<String>) {
        writer.println("")
        writer.println("class $qname(__name: String = \"${objectName()}\"): ObjectNode(__name) {")
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
        qname = this.name.backquote(),
        fields = this.fieldDefinitions.map { it.convert() },
        implements = this.implements.map { it.baseTypeName() }
    )
}
