plugins {
    kotlin("jvm") version "1.3.72"
    java
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
}

group = "com.github.lusingander"
version = "0.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.graphql-java:graphql-java:17.3")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.assertj:assertj-core:3.17.2")
}


tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("kraphqlPlugin") {
            id = "com.github.lusingander.kraphql-plugin"
            implementationClass = "com.github.lusingander.kraphql.KraphQLPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/lusingander/kraphql"
    vcsUrl = "https://github.com/lusingander/kraphql"
    description = "Generate Kotlin DSL from GraphQL schema"
    (plugins) {
        "kraphqlPlugin" {
            displayName = "KraphQL Plugin"
            tags = listOf("kotlin", "graphql", "code-generation")
            version = "0.0.2"
        }
    }
}

tasks {

    val outputDirBase = "./src/test/kotlin/com/github/lusingander/kraphql/gen"

    val sdlDir = file("src/test/resources/sdl/")
    val sdlFiles = sdlDir.listFiles()
    sdlFiles.forEach {
        val outputDir = "$outputDirBase/${it.nameWithoutExtension}"
        mkdir(outputDir)
        register("generate-${it.name}", JavaExec::class.java) {
            main = "com.github.lusingander.kraphql.KraphQLKt"
            classpath = sourceSets["main"].runtimeClasspath
            group = "generate"
            args = listOf(
                it.absolutePath,
                "$outputDir/gen.kt",
                "com.github.lusingander.kraphql.gen.${it.nameWithoutExtension}"
            )
        }
    }

    register("generate") {
        group = "build"
        sdlFiles.forEach {
            dependsOn("generate-${it.name}")
        }
    }
}