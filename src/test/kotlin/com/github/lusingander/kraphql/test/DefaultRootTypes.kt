package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.DefaultRootTypes.query
import com.github.lusingander.kraphql.gen.DefaultRootTypes.mutation
import com.github.lusingander.kraphql.gen.DefaultRootTypes.subscription
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultRootTypes {

    @Test
    fun query() {
        val q = query {
            foo(id = "bar") {
                name
            }
        }
        val expected = """
            query {
              foo(id: "bar") {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun mutation() {
        val q = mutation {
            foo(id = "bar") {
                name
            }
        }
        val expected = """
            mutation {
              foo(id: "bar") {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun subscription() {
        val q = subscription {
            foo(id = "bar") {
                name
            }
        }
        val expected = """
            subscription {
              foo(id: "bar") {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}