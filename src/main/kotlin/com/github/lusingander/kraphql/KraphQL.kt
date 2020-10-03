package com.github.lusingander.kraphql

import com.github.lusingander.kraphql.sdl.SdlLoader
import com.github.lusingander.kraphql.sdl.SdlParser

fun main() {
    val sdls = SdlLoader().load()
    sdls.forEach { SdlParser(it).parse() }
}
