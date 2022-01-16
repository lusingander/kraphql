package com.github.lusingander.kraphql.sdl

import com.github.lusingander.kraphql.dsl.Types
import com.github.lusingander.kraphql.dsl.convert
import graphql.language.*
import graphql.parser.Parser
import graphql.parser.ParserOptions

// https://github.com/graphql-java/graphql-java

class SdlParser(
    private val src: String
) {

    companion object {
        // https://github.com/graphql-java/graphql-java/pull/2549
        // default value is 15,000
        private const val PARSER_MAX_TOKEN_NUMBER = 1_000_000
    }

    fun parse(): Types {
        val options = ParserOptions.getDefaultParserOptions().transform { it.maxTokens(PARSER_MAX_TOKEN_NUMBER) }
        val doc = Parser().parseDocument(src, options)

        val objectTypeDefinitions = doc.getDefinitionsOfType(ObjectTypeDefinition::class.java)
        val scalarTypeDefinitions = doc.getDefinitionsOfType(ScalarTypeDefinition::class.java)
        val enumTypeDefinitions = doc.getDefinitionsOfType(EnumTypeDefinition::class.java)
        val interfaceTypeDefinitions = doc.getDefinitionsOfType(InterfaceTypeDefinition::class.java)
        val unionTypeDefinitions = doc.getDefinitionsOfType(UnionTypeDefinition::class.java)
        val inputObjectTypeDefinitions = doc.getDefinitionsOfType(InputObjectTypeDefinition::class.java)

        val objectTypes = objectTypeDefinitions.map { it.convert() }
        val scalarTypes = scalarTypeDefinitions.map { it.convert() }
        val enumTypes = enumTypeDefinitions.map { it.convert() }
        val interfaceTypes = interfaceTypeDefinitions.map { it.convert(objectTypes) }
        val unionTypes = unionTypeDefinitions.map { it.convert() }
        val inputObjectTypes = inputObjectTypeDefinitions.map { it.convert() }

        return Types(
            objectTypes = objectTypes,
            scalarTypes = scalarTypes,
            enumTypes = enumTypes,
            interfaceTypes = interfaceTypes,
            unionTypes = unionTypes,
            inputObjectTypes = inputObjectTypes
        )
    }
}
