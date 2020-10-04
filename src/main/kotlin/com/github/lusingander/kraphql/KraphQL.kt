package com.github.lusingander.kraphql

import com.github.lusingander.kraphql.dsl.DslBuilder
import com.github.lusingander.kraphql.sdl.SdlParser
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import java.io.File
import java.io.PrintWriter

fun main(args: Array<String>) {
    val argParser = ArgParser("kraphql")
    val input by argParser.option(ArgType.String, shortName = "i", description = "Input SDL file path").required()
    val output by argParser.option(ArgType.String, shortName = "o", description = "Output .kt file path").required()
    argParser.parse(args)

    val sdl = File(input).readText()
    val parsed = SdlParser(sdl).parse()

    File(output).printWriter().use { writer ->
        val builder = DslBuilder(writer)
        builder.build(parsed)
    }
}
