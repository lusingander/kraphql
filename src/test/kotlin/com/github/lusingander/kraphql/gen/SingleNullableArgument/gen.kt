package com.github.lusingander.kraphql.gen.SingleNullableArgument

fun query(init: Query.() -> Unit) = Query().apply(init)

typealias ID = String
typealias MyScalar = String

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

enum class Color {
    RED,
    BLUE,
    GREEN,
    ;
}


class Query(__name: String = "query"): ObjectNode(__name) {
    fun argsId(id: ID? = null, init: Foo.() -> Unit) =
        Foo("argsId").apply { addArgs("id", id) }.also { doInit(it, init) }
    fun argsString(name: String? = null, init: Foo.() -> Unit) =
        Foo("argsString").apply { addArgs("name", name) }.also { doInit(it, init) }
    fun argsInt(value: Int? = null, init: Foo.() -> Unit) =
        Foo("argsInt").apply { addArgs("value", value) }.also { doInit(it, init) }
    fun argsFloat(rate: Float? = null, init: Foo.() -> Unit) =
        Foo("argsFloat").apply { addArgs("rate", rate) }.also { doInit(it, init) }
    fun argsBoolean(isFoo: Boolean? = null, init: Foo.() -> Unit) =
        Foo("argsBoolean").apply { addArgs("isFoo", isFoo) }.also { doInit(it, init) }
    fun argsEnum(color: Color? = null, init: Foo.() -> Unit) =
        Foo("argsEnum").apply { addArgs("color", color) }.also { doInit(it, init) }
    fun argsCustomScalar(my: MyScalar? = null, init: Foo.() -> Unit) =
        Foo("argsCustomScalar").apply { addArgs("my", my) }.also { doInit(it, init) }
    fun argsIdWithDefault(id: ID? = null, init: Foo.() -> Unit) =
        Foo("argsIdWithDefault").apply { addArgs("id", id) }.also { doInit(it, init) }
    fun argsStringWithDefault(name: String? = null, init: Foo.() -> Unit) =
        Foo("argsStringWithDefault").apply { addArgs("name", name) }.also { doInit(it, init) }
    fun argsIntWithDefault(value: Int? = null, init: Foo.() -> Unit) =
        Foo("argsIntWithDefault").apply { addArgs("value", value) }.also { doInit(it, init) }
    fun argsFloatWithDefault(rate: Float? = null, init: Foo.() -> Unit) =
        Foo("argsFloatWithDefault").apply { addArgs("rate", rate) }.also { doInit(it, init) }
    fun argsBooleanWithDefault(isFoo: Boolean? = null, init: Foo.() -> Unit) =
        Foo("argsBooleanWithDefault").apply { addArgs("isFoo", isFoo) }.also { doInit(it, init) }
    fun argsEnumWithDefault(color: Color? = null, init: Foo.() -> Unit) =
        Foo("argsEnumWithDefault").apply { addArgs("color", color) }.also { doInit(it, init) }
    fun argsCustomScalarWithDefault(my: MyScalar? = null, init: Foo.() -> Unit) =
        Foo("argsCustomScalarWithDefault").apply { addArgs("my", my) }.also { doInit(it, init) }
}

class Foo(__name: String = "Foo"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val value get() =
        ScalarNode("value").also { doInit(it) }
    val rate get() =
        ScalarNode("rate").also { doInit(it) }
    val isFoo get() =
        ScalarNode("isFoo").also { doInit(it) }
    val color get() =
        ScalarNode("color").also { doInit(it) }
    val my get() =
        ScalarNode("my").also { doInit(it) }
}
