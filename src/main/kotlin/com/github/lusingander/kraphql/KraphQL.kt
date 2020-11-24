package com.github.lusingander.kraphql

import com.github.lusingander.kraphql.dsl.Configuration
import com.github.lusingander.kraphql.dsl.DslBuilder
import com.github.lusingander.kraphql.sdl.SdlParser
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import java.io.File

private const val TASK_NAME = "kraphqlGenerateDsl"
private const val TASK_EXTENSION_NAME = "kraphql"

open class KraphQLExtension {
    var input: String? = null
    var output: String? = null
    var packageName: String? = "com.example"
}

open class KraphQLTask : DefaultTask() {

    @TaskAction
    fun action() {
        val extension = project.extensions.getByName(TASK_EXTENSION_NAME) as KraphQLExtension
        val input = extension.input
        val output = extension.output
        val packageName = extension.packageName

        if (input.isNullOrEmpty()) {
            throw GradleException("input must not be empty")
        }
        if (output.isNullOrEmpty()) {
            throw GradleException("output must not be empty")
        }
        if (packageName.isNullOrEmpty()) {
            throw GradleException("package must not be empty")
        }

        val configuration = Configuration(
            packageName = packageName
        )

        val sdl = File(input).readText()
        val parsed = SdlParser(sdl).parse()

        File(output).printWriter().use { writer ->
            val builder = DslBuilder(configuration, writer)
            builder.build(parsed)
        }
    }
}

class KraphQLPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        project.extensions.create(TASK_EXTENSION_NAME, KraphQLExtension::class.java)
        project.tasks.create(TASK_NAME, KraphQLTask::class.java) {
            description = "Generate DSL definitions"
        }
    }
}
