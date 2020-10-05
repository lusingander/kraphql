plugins {
    kotlin("jvm") version "1.3.72"
    java
}

group = "com.github.lusingander.kraphql"
version = "0.0.1"

repositories {
    mavenCentral()
    maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.graphql-java:graphql-java:2020-10-01T21-06-37-558aeaa")
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
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
                "--package",
                "com.github.lusingander.kraphql.gen.${it.nameWithoutExtension}",
                "--input",
                it.absolutePath,
                "--output",
                "$outputDir/gen.kt"
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