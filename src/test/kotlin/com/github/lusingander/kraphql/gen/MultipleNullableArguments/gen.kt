package com.github.lusingander.kraphql.gen.MultipleNullableArguments

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
        val argsStr = args.map { "${it.key}: ${it.value}" }.joinToString(separator = ", ")
        return "$__name($argsStr)"
    }
}

enum class Size {
    LARGE,
    MEDIUM,
    SMALL,
    ;
}


class Query(__name: String = "query"): ObjectNode(__name) {
    fun query1(key1: String? = null, key2: String? = null, init: Book.() -> Unit) =
        Book("query1").apply { addArgs("key1", key1) }.apply { addArgs("key2", key2) }.also { doInit(it, init) }
    fun query2(key1: Int? = null, key2: Int? = null, init: Book.() -> Unit) =
        Book("query2").apply { addArgs("key1", key1) }.apply { addArgs("key2", key2) }.also { doInit(it, init) }
    fun query3(key1: Float? = null, key2: Float? = null, init: Book.() -> Unit) =
        Book("query3").apply { addArgs("key1", key1) }.apply { addArgs("key2", key2) }.also { doInit(it, init) }
    fun query4(key1: Boolean? = null, key2: Boolean? = null, key3: Size? = null, init: Book.() -> Unit) =
        Book("query4").apply { addArgs("key1", key1) }.apply { addArgs("key2", key2) }.apply { addArgs("key3", key3) }.also { doInit(it, init) }
}

class Book(__name: String = "Book"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val title get() =
        ScalarNode("title").also { doInit(it) }
    val author get() =
        ScalarNode("author").also { doInit(it) }
    val page get() =
        ScalarNode("page").also { doInit(it) }
    val size get() =
        ScalarNode("size").also { doInit(it) }
}
