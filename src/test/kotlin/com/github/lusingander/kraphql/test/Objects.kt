package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Objects.query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Objects {

    @Test
    fun query1() {
        val q = query {
            books {
                title
                author {
                    firstName
                    lastName
                }
            }
        }
        val expected = """
            query {
              books {
                title
                author {
                  firstName
                  lastName
                }
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query2() {
        val q = query {
            authors {
                firstName
                lastName
                books {
                    title
                }
            }
        }
        val expected = """
            query {
              authors {
                firstName
                lastName
                books {
                  title
                }
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun query3() {
        val q = query {
            books {
                author {
                    books {
                        title
                    }
                }
            }
        }
        val expected = """
            query {
              books {
                author {
                  books {
                    title
                  }
                }
              }
            }
        """.trimIndent()

        val actual = formatQuery(q.toString())

        assertThat(actual).isEqualTo(expected)
    }
}
