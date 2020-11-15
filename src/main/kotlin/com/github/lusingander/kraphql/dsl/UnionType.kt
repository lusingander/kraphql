package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.RootType
import com.github.lusingander.kraphql.graphql.baseTypeName
import com.github.lusingander.kraphql.kotlin.backquote
import graphql.language.UnionTypeDefinition
import java.io.PrintWriter

class UnionType(
    val name: String,
    val qname: String,
    val members: List<String>
) {

    fun build(writer: PrintWriter) {
        writer.println("")
        writer.println("class $qname(__name: String = \"${objectName()}\"): ObjectNode(__name) {")
        members.forEach {
            writer.println("    fun `on $it`(init: $it.() -> Unit) =")
            writer.println("        $it(\"...on $it\").also { doInit(it, init) }")
        }
        writer.println("}")
    }

    private fun objectName(): String =
        if (name in RootType.labels()) name.toLowerCase() else name
}

fun UnionTypeDefinition.convert(): UnionType {
    this.memberTypes
    return UnionType(
        name = this.name,
        qname = this.name.backquote(),
        members = this.memberTypes.map { it.baseTypeName() }
    )
}
