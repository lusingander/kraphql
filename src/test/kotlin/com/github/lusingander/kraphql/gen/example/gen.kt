package com.github.lusingander.kraphql.gen.example

fun query(init: Query.() -> Unit) = Query().apply(init)
fun mutation(init: Mutation.() -> Unit) = Mutation().apply(init)
fun subscription(init: Subscription.() -> Unit) = Subscription().apply(init)

typealias ID = String
typealias Upload = String

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

enum class CacheControlScope {
    PUBLIC,
    PRIVATE,
    ;
}

enum class LiftStatus {
    OPEN,
    CLOSED,
    HOLD,
    ;
}

enum class TrailStatus {
    OPEN,
    CLOSED,
    ;
}


class Lift(__name: String = "Lift"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val capacity get() =
        ScalarNode("capacity").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    val elevationGain get() =
        ScalarNode("elevationGain").also { doInit(it) }
    fun trailAccess(init: Trail.() -> Unit) =
        Trail("trailAccess").also { doInit(it, init) }
}

class Mutation(__name: String = "mutation"): ObjectNode(__name) {
    fun setLiftStatus(id: ID, status: LiftStatus, init: Lift.() -> Unit) =
        Lift("setLiftStatus").apply { addArgs("id", id) }.apply { addArgs("status", status) }.also { doInit(it, init) }
    fun setTrailStatus(id: ID, status: TrailStatus, init: Trail.() -> Unit) =
        Trail("setTrailStatus").apply { addArgs("id", id) }.apply { addArgs("status", status) }.also { doInit(it, init) }
}

class Query(__name: String = "query"): ObjectNode(__name) {
    fun allLifts(status: LiftStatus? = null, init: Lift.() -> Unit) =
        Lift("allLifts").apply { addArgs("status", status) }.also { doInit(it, init) }
    fun allTrails(status: TrailStatus? = null, init: Trail.() -> Unit) =
        Trail("allTrails").apply { addArgs("status", status) }.also { doInit(it, init) }
    fun Lift(id: ID, init: Lift.() -> Unit) =
        Lift("Lift").apply { addArgs("id", id) }.also { doInit(it, init) }
    fun Trail(id: ID, init: Trail.() -> Unit) =
        Trail("Trail").apply { addArgs("id", id) }.also { doInit(it, init) }
    fun liftCount(status: LiftStatus? = null) =
        ScalarWithArgsNode("liftCount", mapOf("status" to status)).also { doInit(it) }
    fun trailCount(status: TrailStatus? = null) =
        ScalarWithArgsNode("trailCount", mapOf("status" to status)).also { doInit(it) }
    val gnar get() =
        ScalarNode("gnar").also { doInit(it) }
    val sweet get() =
        ScalarNode("sweet").also { doInit(it) }
}

class Subscription(__name: String = "subscription"): ObjectNode(__name) {
    fun liftStatusChange(init: Lift.() -> Unit) =
        Lift("liftStatusChange").also { doInit(it, init) }
    fun trailStatusChange(init: Trail.() -> Unit) =
        Trail("trailStatusChange").also { doInit(it, init) }
}

class Trail(__name: String = "Trail"): ObjectNode(__name) {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val difficulty get() =
        ScalarNode("difficulty").also { doInit(it) }
    val groomed get() =
        ScalarNode("groomed").also { doInit(it) }
    val trees get() =
        ScalarNode("trees").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    fun accessedByLifts(init: Lift.() -> Unit) =
        Lift("accessedByLifts").also { doInit(it, init) }
}
