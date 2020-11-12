package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Input.FooInput
import com.github.lusingander.kraphql.gen.Input.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Input {

    @Test
    fun input_notNull() {
        val q = query {
            foo(input = FooInput(name = "abc123", value = 456)) {
                id
                name
                value
            }
        }
        val expected = """
            query {
              foo(input: {name : "abc123", value : 456}) {
                id
                name
                value
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun input_nullable() {
        val q = query {
            fooOpt {
                id
                name
                value
            }
        }
        val expected = """
            query {
              fooOpt {
                id
                name
                value
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}