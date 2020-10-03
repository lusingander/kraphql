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
}

application {
    mainClassName = "com.github.lusingander.kraphql.KraphQLKt"
}