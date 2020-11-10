package com.github.lusingander.kraphql.gen.MultipleNonNullArguments

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

enum class Size {
    LARGE,
    MEDIUM,
    SMALL,
    ;
}


class Query(__name: String = "query"): ObjectNode(__name) {
    fun bookById(id: ID, init: Book.() -> Unit) =
        Book("bookById").apply { addArgs("id", id) }.also { doInit(it, init) }
    fun books1(publisher: String, author: String, init: Book.() -> Unit) =
        Book("books1").apply { addArgs("publisher", publisher) }.apply { addArgs("author", author) }.also { doInit(it, init) }
    fun books2(priceMin: Float, priceMax: Float, init: Book.() -> Unit) =
        Book("books2").apply { addArgs("priceMin", priceMin) }.apply { addArgs("priceMax", priceMax) }.also { doInit(it, init) }
    fun books3(pageMin: Int, pageMax: Int, size: Size, init: Book.() -> Unit) =
        Book("books3").apply { addArgs("pageMin", pageMin) }.apply { addArgs("pageMax", pageMax) }.apply { addArgs("size", size) }.also { doInit(it, init) }
}

class Book(__name: String = "Book"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val publisher get() =
        ScalarNode("publisher").also { doInit(it) }
    val title get() =
        ScalarNode("title").also { doInit(it) }
    val author get() =
        ScalarNode("author").also { doInit(it) }
    val page get() =
        ScalarNode("page").also { doInit(it) }
    val price get() =
        ScalarNode("price").also { doInit(it) }
    val size get() =
        ScalarNode("size").also { doInit(it) }
}
