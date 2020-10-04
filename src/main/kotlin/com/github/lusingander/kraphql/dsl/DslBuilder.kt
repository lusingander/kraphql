package com.github.lusingander.kraphql.dsl

import java.io.PrintWriter

class DslBuilder(
    private val configuration: Configuration,
    private val writer: PrintWriter
) {

    fun build(types: Types) {
        writer.println("package ${configuration.packageName}")
        writer.println("")
        if (types.existQuery()) {
            writer.println("fun query(init: Query.() -> Unit) = Query().apply(init)")
        }
        if (types.existMutation()) {
            writer.println("fun mutation(init: Mutation.() -> Unit) = Mutation().apply(init)")
        }
        if (types.existSubscription()) {
            writer.println("fun subscription(init: Subscription.() -> Unit) = Subscription().apply(init)")
        }
        writer.println("")
        writer.println("typealias ID = String")
        writer.println("")
        writer.println("@DslMarker")
        writer.println("annotation class GraphQLMarker")
        writer.println("")
        writer.println("@GraphQLMarker")
        writer.println("open class ObjectNode(protected val __name: String) {")
        writer.println("    val children = mutableListOf<ObjectNode>()")
        writer.println("    val argsMap: MutableMap<String, Any?> = mutableMapOf()")
        writer.println("")
        writer.println("    fun <T : ObjectNode> doInit(child: T, init: T.() -> Unit = {}) {")
        writer.println("        child.init()")
        writer.println("        children.add(child)")
        writer.println("    }")
        writer.println("")
        writer.println("    fun addArgs(k: String, v: Any?) = argsMap.put(k, v)")
        writer.println("")
        writer.println("    override fun toString() =")
        writer.println("        \"\$__name\${argsStr()} { \${children.joinToString(\" \")} }\"")
        writer.println("")
        writer.println("    private fun argsStr(): String {")
        writer.println("        val filtered = argsMap.filter { (_, v) ->")
        writer.println("            v != null")
        writer.println("        }")
        writer.println("        if (filtered.isEmpty()) {")
        writer.println("            return \"\"")
        writer.println("        }")
        writer.println("        val str = filtered.map { (k, v) ->")
        writer.println("            \"\$k: \$v\"")
        writer.println("        }.joinToString(separator = \", \")")
        writer.println("        return \"(\$str)\"")
        writer.println("    }")
        writer.println("}")
        writer.println("")
        writer.println("open class ScalarNode(__name: String): ObjectNode(__name) {")
        writer.println("    override fun toString() = __name")
        writer.println("}")
        writer.println("")
        writer.println("open class ScalarWithArgsNode(__name: String, private val args: Map<String, Any?>): ObjectNode(__name) {")
        writer.println("    override fun toString(): String {")
        writer.println("        val argsStr = args.map { \"\${it.key}: \${it.value}\" }.joinToString(separator = \", \")")
        writer.println("        return \"\$__name(\$argsStr)\"")
        writer.println("    }")
        writer.println("}")
        writer.println("")
        types.enumTypes.forEach {
            it.build(writer)
            writer.println("")
        }
        types.objectTypes.forEach {
            it.build(writer, types.scalars())
        }
    }
}
