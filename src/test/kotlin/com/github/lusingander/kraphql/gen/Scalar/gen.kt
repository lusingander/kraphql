// code generated by https://github.com/lusingander/kraphql (v0.0.4)

package com.github.lusingander.kraphql.gen.Scalar

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

    fun toEscapedString() = toString().replace("\"", "\\\"")

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

class Foo(__name: String = "Foo"): ObjectNode(__name) {
    val foo1 get() =
        ScalarNode("foo1").also { doInit(it) }
    val foo2 get() =
        ScalarNode("foo2").also { doInit(it) }
    val Foo3 get() =
        ScalarNode("Foo3").also { doInit(it) }
    val FOO4 get() =
        ScalarNode("FOO4").also { doInit(it) }
    val fOo5 get() =
        ScalarNode("fOo5").also { doInit(it) }
}
