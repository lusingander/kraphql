package com.github.lusingander.kraphql.test

import com.github.lusingander.kraphql.gen.Query1.query

class Query1 {

    fun test() {
        val q = query {
            foo {
                foo1
                foo2
                foo3
                foo4
                foo5
            }
        }
        println(q.toString())
    }
}