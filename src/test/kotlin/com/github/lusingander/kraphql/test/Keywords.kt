package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Keywords.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Keywords {

    @Test
    fun keywords() {
        val q = query {
            foo {
                `package`
                `object`(`class` = "abc123", `null` = false)
            }
        }
        val expected = """
            query {
              foo {
                package
                object(class: "abc123", null: false)
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}