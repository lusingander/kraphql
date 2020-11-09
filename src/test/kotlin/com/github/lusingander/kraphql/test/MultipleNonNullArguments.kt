package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.MultipleNonNullArguments.Size
import com.github.lusingander.kraphql.gen.MultipleNonNullArguments.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MultipleNonNullArguments {

    @Test
    fun bookById() {
        val q = query {
            bookById(id = "abc123") {
                title
                author
                page
            }
        }
        val expected = """
            query {
              bookById(id: "abc123") {
                title
                author
                page
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun books1() {
        val q = query {
            books1(author = "foo", publisher = "bar") {
                price
                page
            }
        }
        val expected = """
            query {
              books1(publisher: "bar", author: "foo") {
                price
                page
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun books2() {
        val q = query {
            books2(priceMin = 2.34f, priceMax = 6.78f) {
                price
                size
                title
            }
        }
        val expected = """
            query {
              books2(priceMin: 2.34, priceMax: 6.78) {
                price
                size
                title
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun books3() {
        val q = query {
            books3(pageMax = 300, size = Size.MEDIUM, pageMin = 200) {
                id
                publisher
            }
        }
        val expected = """
            query {
              books3(pageMin: 200, pageMax: 300, size: MEDIUM) {
                id
                publisher
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}