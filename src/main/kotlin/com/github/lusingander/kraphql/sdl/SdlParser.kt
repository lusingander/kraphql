package com.github.lusingander.kraphql.sdl

import graphql.parser.Parser

// https://github.com/graphql-java/graphql-java

class SdlParser(
    private val src: String
) {

    fun parse() {
        val doc = Parser.parse(src)
        doc.definitions.forEach(::println)
    }
}