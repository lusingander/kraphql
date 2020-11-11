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
        types.customScalars().forEach {
            writer.println("typealias $it = String")
        }
        writer.println("")
        writer.println("@DslMarker")
        writer.println("annotation class GraphQLMarker")
        writer.println("")
        writer.println("@GraphQLMarker")
        writer.println("open class ObjectNode(protected val __name: String) {")
        writer.println("    private val children = mutableListOf<ObjectNode>()")
        writer.println("    private val argsMap: MutableMap<String, Any?> = mutableMapOf()")
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
        writer.println("            if (v is String) \"\$k: \\\"\$v\\\"\" else \"\$k: \$v\"")
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
        writer.println("        val filtered = args.filter { (_, v) ->")
        writer.println("            v != null")
        writer.println("        }")
        writer.println("        if (filtered.isEmpty()) {")
        writer.println("            return __name")
        writer.println("        }")
        writer.println("        val argsStr = filtered.map {")
        writer.println("            if (it.value is String) \"\${it.key}: \\\"\${it.value}\\\"\" else \"\${it.key}: \${it.value}\"")
        writer.println("        }.joinToString(separator = \", \")")
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
        types.interfaceTypes.forEach {
            it.build(writer, types.scalars())
        }
    }
}
