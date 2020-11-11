package com.github.lusingander.kraphql.sdl

import com.github.lusingander.kraphql.dsl.Types
import com.github.lusingander.kraphql.dsl.convert
import graphql.language.EnumTypeDefinition
import graphql.language.InterfaceTypeDefinition
import graphql.language.ObjectTypeDefinition
import graphql.language.ScalarTypeDefinition
import graphql.parser.Parser

// https://github.com/graphql-java/graphql-java

class SdlParser(
    private val src: String
) {

    fun parse(): Types {
        val doc = Parser.parse(src)

        val objectTypeDefinitions = doc.getDefinitionsOfType(ObjectTypeDefinition::class.java)
        val scalarTypeDefinitions = doc.getDefinitionsOfType(ScalarTypeDefinition::class.java)
        val enumTypeDefinitions = doc.getDefinitionsOfType(EnumTypeDefinition::class.java)
        val interfaceTypeDefinitions = doc.getDefinitionsOfType(InterfaceTypeDefinition::class.java)

        val objectTypes = objectTypeDefinitions.map { it.convert() }
        val scalarTypes = scalarTypeDefinitions.map { it.convert() }
        val enumTypes = enumTypeDefinitions.map { it.convert() }
        val interfaceTypes = interfaceTypeDefinitions.map { it.convert(objectTypes) }

        return Types(
            objectTypes = objectTypes,
            scalarTypes = scalarTypes,
            enumTypes = enumTypes,
            interfaceTypes = interfaceTypes
        )
    }
}
