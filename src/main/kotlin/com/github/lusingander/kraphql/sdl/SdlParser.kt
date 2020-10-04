package com.github.lusingander.kraphql.sdl

import com.github.lusingander.kraphql.dsl.Types
import com.github.lusingander.kraphql.dsl.convert
import graphql.language.EnumTypeDefinition
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

        return Types(
            objectTypes = objectTypeDefinitions.map { it.convert() },
            scalarTypes = scalarTypeDefinitions.map { it.convert() },
            enumTypes = enumTypeDefinitions.map { it.convert() }
        )
    }
}
