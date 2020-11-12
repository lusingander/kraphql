package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.DefaultDirective.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultDirectives {

    @Test
    fun deprecated() {
        val q = query {
            foo {
                id
                //name
            }
        }
        val expected = """
            query {
              foo {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}