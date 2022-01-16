package com.github.lusingander.kraphql.sdl

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class SdlParserTest {

    @Test
    fun exceptionNotThrown() {
        val input = """
            type Query {
              foo
            }
        """.trimIndent()

        val parser = SdlParser(input.repeat(200000)) // 1_000_000 / 5

        Assertions.assertThatCode { parser.parse() }.doesNotThrowAnyException()
    }

    @Test
    fun exceptionThrownBecauseOfTooManyToken() {
        val input = """
            type Query {
              foo
            }
        """.trimIndent()

        val parser = SdlParser(input.repeat(200001))

        Assertions.assertThatThrownBy {
            parser.parse()
        }
    }
}