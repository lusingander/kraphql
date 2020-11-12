package com.github.lusingander.kraphql.dsl

import com.github.lusingander.kraphql.graphql.RootType
import com.github.lusingander.kraphql.graphql.ScalarType

data class Types(
    val objectTypes: List<ObjectType>,
    val scalarTypes: List<CustomScalarType>,
    val enumTypes: List<EnumType>,
    val interfaceTypes: List<InterfaceType>,
    val unionTypes: List<UnionType>
) {

    fun scalars(): Set<String> = ScalarType.labels() + customScalars() + enums()
    fun customScalars(): Set<String> = scalarTypes.map { it.name }.toSet()

    fun existQuery(): Boolean = existRootTypeOf(RootType.QUERY)
    fun existMutation(): Boolean = existRootTypeOf(RootType.MUTATION)
    fun existSubscription(): Boolean = existRootTypeOf(RootType.SUBSCRIPTION)

    private fun enums(): Set<String> = enumTypes.map { it.name }.toSet()

    private fun existRootTypeOf(type: RootType): Boolean = objectTypes.any { it.name == type.label }
}
