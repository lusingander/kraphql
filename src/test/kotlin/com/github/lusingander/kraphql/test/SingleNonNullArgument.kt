package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.SingleNonNullArgument.Color
import com.github.lusingander.kraphql.gen.SingleNonNullArgument.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleNonNullArgument {

    @Test
    fun argsId() {
        val q = query {
            argsId(id = "abc123") {
                id
            }
        }
        val expected = """
            query {
              argsId(id: "abc123") {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsString() {
        val q = query {
            argsString(name = "foo bar") {
                name
            }
        }
        val expected = """
            query {
              argsString(name: "foo bar") {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsInt() {
        val q = query {
            argsInt(value = 100) {
                value
            }
        }
        val expected = """
            query {
              argsInt(value: 100) {
                value
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsFloat() {
        val q = query {
            argsFloat(rate = 0.05f) {
                rate
            }
        }
        val expected = """
            query {
              argsFloat(rate: 0.05) {
                rate
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsBoolean() {
        val q = query {
            argsBoolean(isFoo = true) {
                isFoo
            }
        }
        val expected = """
            query {
              argsBoolean(isFoo: true) {
                isFoo
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsEnum() {
        val q = query {
            argsEnum(color = Color.BLUE) {
                color
            }
        }
        val expected = """
            query {
              argsEnum(color: BLUE) {
                color
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsCustomScalar() {
        val q = query {
            argsCustomScalar(my = "custom scalar") {
                my
            }
        }
        val expected = """
            query {
              argsCustomScalar(my: "custom scalar") {
                my
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsScalar() {
        val q = query {
            argsReturnsScalar(id = "abc123")
        }
        val expected = """
            query {
              argsReturnsScalar(id: "abc123")
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsCustomScalar() {
        val q = query {
            argsReturnsCustomScalar(id = "abc123")
        }
        val expected = """
            query {
              argsReturnsCustomScalar(id: "abc123")
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}