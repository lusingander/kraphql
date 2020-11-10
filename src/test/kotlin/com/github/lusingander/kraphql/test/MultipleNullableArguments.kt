package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.MultipleNullableArguments.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MultipleNullableArguments {

    @Test
    fun query1_allArgumentsAreNull() {
        val q = query {
            query1 {
                id
            }
        }
        val expected = """
            query {
              query1 {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query1_key1IsNotNull() {
        val q = query {
            query1(key1 = "value1") {
                id
            }
        }
        val expected = """
            query {
              query1(key1: "value1") {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query1_key2IsNotNull() {
        val q = query {
            query1(key2 = "value2") {
                id
            }
        }
        val expected = """
            query {
              query1(key2: "value2") {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query1_allArgumentsAreNotNull() {
        val q = query {
            query1(key2 = "value2", key1 = "value1") {
                id
            }
        }
        val expected = """
            query {
              query1(key1: "value1", key2: "value2") {
                id
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query2_allArgumentsAreNull() {
        val q = query {
            query2 {
                title
            }
        }
        val expected = """
            query {
              query2 {
                title
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query2_allArgumentsAreNotNull() {
        val q = query {
            query2(key1 = 1, key2 = 2) {
                title
            }
        }
        val expected = """
            query {
              query2(key1: 1, key2: 2) {
                title
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query3_allArgumentsAreNull() {
        val q = query {
            query3 {
                author
            }
        }
        val expected = """
            query {
              query3 {
                author
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query4_allArgumentsAreNull() {
        val q = query {
            query4 {
                page
            }
        }
        val expected = """
            query {
              query4 {
                page
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}