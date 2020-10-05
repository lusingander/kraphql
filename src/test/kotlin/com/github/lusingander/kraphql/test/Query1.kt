package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Query1.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Query1 {

    @Test
    fun nonNullScalar() {
        val q = query {
            foo {
                foo1
                foo2
                Foo3
                FOO4
                fOo5
            }
        }
        val expected = """
            query {
              foo {
                foo1
                foo2
                Foo3
                FOO4
                fOo5
              }
            }
            """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}