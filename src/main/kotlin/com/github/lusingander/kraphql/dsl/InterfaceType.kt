package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.RootType
import com.github.lusingander.kraphql.kotlin.backquote
import graphql.language.InterfaceTypeDefinition
import java.io.PrintWriter

class InterfaceType(
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
        implements.forEach {
            writer.println("    fun `on $it`(init: $it.() -> Unit) =")
            writer.println("        $it(\"...on $it\").also { doInit(it, init) }")
        }
        writer.println("}")
    }

    private fun objectName(): String =
        if (name in RootType.labels()) name.toLowerCase() else name
}

fun InterfaceTypeDefinition.convert(objectTypes: List<ObjectType>): InterfaceType {
    return InterfaceType(
        name = this.name,
        qname = this.name.backquote(),
        fields = this.fieldDefinitions.map { it.convert() },
        implements = objectTypes.filter { it.implements.contains(this.name) }.map { it.name }
    )
}
