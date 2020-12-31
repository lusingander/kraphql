plugins {
    kotlin("jvm") version "1.3.72"
    java
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.github.lusingander"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.graphql-java:graphql-java:2020-10-01T21-06-37-558aeaa")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation("org.assertj:assertj-core:3.17.2")
}


tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("kraphql") {
            id = "com.github.lusingander.kraphql"
            implementationClass = "com.github.lusingander.kraphql.KraphQLPlugin"
        }
    }
}
