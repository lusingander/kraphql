package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.CustomScalar.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomScalar {

    @Test
    fun query() {
        val q = query {
            customScalars {
                datetime
                date
                time
            }
        }
        val expected = """
            query {
              customScalars {
                datetime
                date
                time
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}