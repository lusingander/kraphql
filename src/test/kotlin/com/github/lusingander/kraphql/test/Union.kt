package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Union.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Union {

    @Test
    fun query() {
        val q = query {
            foo {
                `on Bar` {
                    id
                    value
                    calc(n = 123)
                }
                `on Baz` {
                    id
                    rate
                    search
                }
            }
        }
        val expected = """
            query {
              foo {
                ... on Bar {
                  id
                  value
                  calc(n: 123)
                }
                ... on Baz {
                  id
                  rate
                  search
                }
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}