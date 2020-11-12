package com.github.lusingander.kraphql.gen.Union

fun query(init: Query.() -> Unit) = Query().apply(init)

typealias ID = String

@DslMarker
annotation class GraphQLMarker

@GraphQLMarker
open class ObjectNode(protected val __name: String) {
    private val children = mutableListOf<ObjectNode>()
    private val argsMap: MutableMap<String, Any?> = mutableMapOf()

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
            if (v is String) "$k: \"$v\"" else "$k: $v"
        }.joinToString(separator = ", ")
        return "($str)"
    }
}

open class ScalarNode(__name: String): ObjectNode(__name) {
    override fun toString() = __name
}

open class ScalarWithArgsNode(__name: String, private val args: Map<String, Any?>): ObjectNode(__name) {
    override fun toString(): String {
        val filtered = args.filter { (_, v) ->
            v != null
        }
        if (filtered.isEmpty()) {
            return __name
        }
        val argsStr = filtered.map {
            if (it.value is String) "${it.key}: \"${it.value}\"" else "${it.key}: ${it.value}"
        }.joinToString(separator = ", ")
        return "$__name($argsStr)"
    }
}


class Query(__name: String = "query"): ObjectNode(__name) {
    fun foo(init: Foo.() -> Unit) =
        Foo("foo").also { doInit(it, init) }
}

class Bar(__name: String = "Bar"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val value get() =
        ScalarNode("value").also { doInit(it) }
    fun calc(n: Int) =
        ScalarWithArgsNode("calc", mapOf("n" to n)).also { doInit(it) }
}

class Baz(__name: String = "Baz"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val rate get() =
        ScalarNode("rate").also { doInit(it) }
    fun search(name: String? = null) =
        ScalarWithArgsNode("search", mapOf("name" to name)).also { doInit(it) }
    val search get() =
        ScalarNode("search").also { doInit(it) }
}

class Foo(__name: String = "Foo"): ObjectNode(__name) {
    fun `on Bar`(init: Bar.() -> Unit) =
        Bar("...on Bar").also { doInit(it, init) }
    fun `on Baz`(init: Baz.() -> Unit) =
        Baz("...on Baz").also { doInit(it, init) }
}