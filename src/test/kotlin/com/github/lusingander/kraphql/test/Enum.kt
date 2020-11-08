package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Enum.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Enum {

    @Test
    fun query() {
        val q = query {
            enumFields {
                color
                episode
            }
        }
        val expected = """
            query {
              enumFields {
                color
                episode
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}