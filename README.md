KraphQL
====

Kotlin DSL Generator for GraphQL

```
[GraphQL schema] --(KraphQL)--> [DSL definition .kt file] <-- Call from your code
```

For example, given the following schema:

```graphql
type Query {
    books: [Book!]!
    authors: [Author!]!
}

type Book {
    title: String!
    author: Author!
}

type Author {
    firstName: String!
    lastName: String!
    books: [Book!]!
}
```

From here, KraphQL generates Kotlin code for the DSL.

Then, we can use it to write the following DSL:

```kotlin
    @Test
    fun query() {
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

        val actual = formatQuery(q.toString()) // formatQuery just makes it look good

        assertThat(actual).isEqualTo(expected)
    }
```

## Sample

See `./src/test/` directory

### Test codes

[./src/test/kotlin/com/github/lusingander/kraphql/test](https://github.com/lusingander/kraphql/tree/master/src/test/kotlin/com/github/lusingander/kraphql/test)

### Schema definitions

[./src/test/resources/sdl](https://github.com/lusingander/kraphql/tree/master/src/test/resources/sdl)

### Generated Kotlin codes

[./src/test/kotlin/com/github/lusingander/kraphql/gen](https://github.com/lusingander/kraphql/tree/master/src/test/kotlin/com/github/lusingander/kraphql/gen)
