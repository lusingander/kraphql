KraphQL
====

[![gradle plugin](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/com/github/lusingander/kraphql-plugin/com.github.lusingander.kraphql-plugin.gradle.plugin/maven-metadata.xml.svg?label=Gradle&style=flat-square)](https://plugins.gradle.org/plugin/com.github.lusingander.kraphql-plugin)

Kotlin DSL Generator for GraphQL

```
[GraphQL schema] --(KraphQL)--> [DSL definition .kt file] <-- Call from your code
```

## About

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

## Usage

KraphQL is available as [Gradle plugin](https://plugins.gradle.org/plugin/com.github.lusingander.kraphql-plugin).

Add the following to build.gradle:

```groovy
plugins {
  id "com.github.lusingander.kraphql-plugin" version "0.0.1"
}

kraphql {
    input = "./schema.graphql"
    output = "./output.kt"
    packageName = "com.github.example"
}
```

Then, when you run the `kraphqlGenerateDsl` task, it will output the Kotlin DSL file.

For an example, see [kraphql-github](https://github.com/lusingander/kraphql-github).

## Sample

See `./src/test/` directory, or see [Related projects](#related-projects).

### Test codes

[./src/test/kotlin/com/github/lusingander/kraphql/test](https://github.com/lusingander/kraphql/tree/master/src/test/kotlin/com/github/lusingander/kraphql/test)

### Schema definitions

[./src/test/resources/sdl](https://github.com/lusingander/kraphql/tree/master/src/test/resources/sdl)

### Generated Kotlin codes

[./src/test/kotlin/com/github/lusingander/kraphql/gen](https://github.com/lusingander/kraphql/tree/master/src/test/kotlin/com/github/lusingander/kraphql/gen)

## Related projects

- [lusingander/kraphql-github](https://github.com/lusingander/kraphql-github)
  - Kotlin DSL for GitHub GraphQL API (GitHub API v4)

