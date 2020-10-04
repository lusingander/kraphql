package com.github.lusingander.kraphql

import com.github.lusingander.kraphql.dsl.DslBuilder
import com.github.lusingander.kraphql.sdl.SdlLoader
import com.github.lusingander.kraphql.sdl.SdlParser
import java.io.PrintWriter

fun main() {
    val sdls = SdlLoader().load()
    val builder = DslBuilder(PrintWriter(System.out, true))

    sdls.map {
        SdlParser(it)
    }.map {
        it.parse()
    }.forEach {
        builder.build(it)
    }
}
