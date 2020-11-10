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

    @Test
    fun argsCustomScalar_null() {
        val q = query {
            argsCustomScalar {
                my
            }
        }
        val expected = """
            query {
              argsCustomScalar {
                my
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsCustomScalar_notNull() {
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
    fun argsReturnsScalar_null() {
        val q = query {
            argsReturnsScalar
        }
        val expected = """
            query {
              argsReturnsScalar
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsScalar_notNull() {
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
    fun argsReturnsCustomScalar_null() {
        val q = query {
            argsReturnsCustomScalar
        }
        val expected = """
            query {
              argsReturnsCustomScalar
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsCustomScalar_notNull() {
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

    @Test
    fun argsIdWithDefault_null() {
        val q = query {
            argsIdWithDefault {
                id
            }
        }
        val expected = """
            query {
              argsIdWithDefault {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsIdWithDefault_notNull() {
        val q = query {
            argsIdWithDefault(id = "abc123") {
                id
            }
        }
        val expected = """
            query {
              argsIdWithDefault(id: "abc123") {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsStringWithDefault_null() {
        val q = query {
            argsStringWithDefault {
                name
            }
        }
        val expected = """
            query {
              argsStringWithDefault {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsStringWithDefault_notNull() {
        val q = query {
            argsStringWithDefault(name = "foo bar") {
                name
            }
        }
        val expected = """
            query {
              argsStringWithDefault(name: "foo bar") {
                name
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsIntWithDefault_null() {
        val q = query {
            argsIntWithDefault {
                value
            }
        }
        val expected = """
            query {
              argsIntWithDefault {
                value
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsIntWithDefault_notNull() {
        val q = query {
            argsIntWithDefault(value = 100) {
                value
            }
        }
        val expected = """
            query {
              argsIntWithDefault(value: 100) {
                value
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsFloatWithDefault_null() {
        val q = query {
            argsFloatWithDefault {
                rate
            }
        }
        val expected = """
            query {
              argsFloatWithDefault {
                rate
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsFloatWithDefault_notNull() {
        val q = query {
            argsFloatWithDefault(rate = 0.05f) {
                rate
            }
        }
        val expected = """
            query {
              argsFloatWithDefault(rate: 0.05) {
                rate
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsBooleanWithDefault_null() {
        val q = query {
            argsBooleanWithDefault {
                isFoo
            }
        }
        val expected = """
            query {
              argsBooleanWithDefault {
                isFoo
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsBooleanWithDefault_notNull() {
        val q = query {
            argsBooleanWithDefault(isFoo = true) {
                isFoo
            }
        }
        val expected = """
            query {
              argsBooleanWithDefault(isFoo: true) {
                isFoo
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsEnumWithDefault_null() {
        val q = query {
            argsEnumWithDefault {
                color
            }
        }
        val expected = """
            query {
              argsEnumWithDefault {
                color
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsEnumWithDefault_notNull() {
        val q = query {
            argsEnumWithDefault(color = Color.BLUE) {
                color
            }
        }
        val expected = """
            query {
              argsEnumWithDefault(color: BLUE) {
                color
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsCustomScalarWithDefault_null() {
        val q = query {
            argsCustomScalarWithDefault {
                my
            }
        }
        val expected = """
            query {
              argsCustomScalarWithDefault {
                my
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsCustomScalarWithDefault_notNull() {
        val q = query {
            argsCustomScalarWithDefault(my = "custom scalar") {
                my
            }
        }
        val expected = """
            query {
              argsCustomScalarWithDefault(my: "custom scalar") {
                my
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsScalarWithDefault_null() {
        val q = query {
            argsReturnsScalarWithDefault
        }
        val expected = """
            query {
              argsReturnsScalarWithDefault
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsScalarWithDefault_notNull() {
        val q = query {
            argsReturnsScalarWithDefault(id = "abc123")
        }
        val expected = """
            query {
              argsReturnsScalarWithDefault(id: "abc123")
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsCustomScalarWithDefault_null() {
        val q = query {
            argsReturnsCustomScalarWithDefault
        }
        val expected = """
            query {
              argsReturnsCustomScalarWithDefault
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun argsReturnsCustomScalarWithDefault_notNull() {
        val q = query {
            argsReturnsCustomScalarWithDefault(id = "abc123")
        }
        val expected = """
            query {
              argsReturnsCustomScalarWithDefault(id: "abc123")
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}