package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.SingleNullableArgument.Color
import com.github.lusingander.kraphql.gen.SingleNullableArgument.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SingleNullableArgument {

    @Test
    fun argsId_null() {
        val q = query {
            argsId {
                id
            }
        }
        val expected = """
            query {
              argsId {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsId_notNull() {
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
    fun argsString_null() {
        val q = query {
            argsString {
                name
            }
        }
        val expected = """
            query {
              argsString {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsString_notNull() {
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
    fun argsInt_null() {
        val q = query {
            argsInt {
                value
            }
        }
        val expected = """
            query {
              argsInt {
                value
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsInt_notNull() {
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
    fun argsFloat_null() {
        val q = query {
            argsFloat {
                rate
            }
        }
        val expected = """
            query {
              argsFloat {
                rate
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsFloat_notNull() {
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
    fun argsBoolean_null() {
        val q = query {
            argsBoolean {
                isFoo
            }
        }
        val expected = """
            query {
              argsBoolean {
                isFoo
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsBoolean_notNull() {
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
    fun argsEnum_null() {
        val q = query {
            argsEnum {
                color
            }
        }
        val expected = """
            query {
              argsEnum {
                color
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsEnum_notNull() {
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
}