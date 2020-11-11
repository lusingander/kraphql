package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Interface.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Interface {

    @Test
    fun query() {
        val q = query {
            foo {
                id
                calc(name = "abc123")
                `on Bar` {
                    id
                    value
                }
                `on Baz` {
                    id
                    rate
                }
            }
        }
        val expected = """
            query {
              foo {
                id
                calc(name: "abc123")
                ... on Bar {
                  id
                  value
                }
                ... on Baz {
                  id
                  rate
                }
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}