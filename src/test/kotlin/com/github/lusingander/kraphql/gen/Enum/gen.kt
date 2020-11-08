package com.github.lusingander.kraphql.gen.Enum

fun query(init: Query.() -> Unit) = Query().apply(init)

typealias ID = String

@DslMarker
annotation class GraphQLMarker

@GraphQLMarker
open class ObjectNode(protected val __name: String) {
    val children = mutableListOf<ObjectNode>()
    val argsMap: MutableMap<String, Any?> = mutableMapOf()

    fun <T : ObjectNode> doInit(child: T, init: T.() -> Unit = {}) {
        child.init()
        children.add(child)
    }

    fun addArgs(k: String, v: Any?) = argsMap.put(k, v)

    override fun toString() =
        "$__name${argsStr()} { ${children.joinToString(" ")} }"

    private fun argsStr(): String {
        val filtered = argsMap.filter { (_, v) ->
            v != null
        }
        if (filtered.isEmpty()) {
            return ""
        }
        val str = filtered.map { (k, v) ->
            "$k: $v"
        }.joinToString(separator = ", ")
        return "($str)"
    }
}

open class ScalarNode(__name: String): ObjectNode(__name) {
    override fun toString() = __name
}

open class ScalarWithArgsNode(__name: String, private val args: Map<String, Any?>): ObjectNode(__name) {
    override fun toString(): String {
        val argsStr = args.map { "${it.key}: ${it.value}" }.joinToString(separator = ", ")
        return "$__name($argsStr)"
    }
}

enum class Color {
    RED,
    BLUE,
    GREEN,
    ;
}

enum class Episode {
    NEWHOPE,
    EMPIRE,
    JEDI,
    ;
}


class Query(__name: String = "query"): ObjectNode(__name) {
    fun enumFields(init: EnumFields.() -> Unit) =
        EnumFields("enumFields").also { doInit(it, init) }
}

class EnumFields(__name: String = "EnumFields"): ObjectNode(__name) {
    val color get() =
        ScalarNode("color").also { doInit(it) }
    val episode get() =
        ScalarNode("episode").also { doInit(it) }
}
