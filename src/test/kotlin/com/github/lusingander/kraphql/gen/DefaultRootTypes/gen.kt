// code generated by https://github.com/lusingander/kraphql (v0.0.1)

package com.github.lusingander.kraphql.gen.DefaultRootTypes

fun query(init: Query.() -> Unit) = Query().apply(init)
fun mutation(init: Mutation.() -> Unit) = Mutation().apply(init)
fun subscription(init: Subscription.() -> Unit) = Subscription().apply(init)

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
    fun foo(id: ID, init: Foo.() -> Unit) =
        Foo("foo").apply { addArgs("id", id) }.also { doInit(it, init) }
}

class Mutation(__name: String = "mutation"): ObjectNode(__name) {
    fun foo(id: ID, init: Foo.() -> Unit) =
        Foo("foo").apply { addArgs("id", id) }.also { doInit(it, init) }
}

class Subscription(__name: String = "subscription"): ObjectNode(__name) {
    fun foo(id: ID, init: Foo.() -> Unit) =
        Foo("foo").apply { addArgs("id", id) }.also { doInit(it, init) }
}

class Foo(__name: String = "Foo"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
}
