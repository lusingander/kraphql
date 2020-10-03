package com.github.lusingander.kraphql.sdl

import java.io.File

class SdlLoader {

    fun load(): List<String> {
        return loadFiles().map { it.readText() }
    }

    private fun loadFiles(): Array<File> {
        val classLoader = Thread.currentThread().contextClassLoader
        val url = classLoader.getResource("sdl")
        return url?.let {
            File(it.path)
        }?.listFiles() ?: emptyArray()
    }
}