plugins {
    kotlin("jvm") version "1.3.72"
    application
}

group = "com.github.lusingander.kraphql"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.graphql-java:graphql-java:2020-10-01T21-06-37-558aeaa")
}

application {
    mainClassName = "com.github.lusingander.kraphql.KraphQLKt"
}