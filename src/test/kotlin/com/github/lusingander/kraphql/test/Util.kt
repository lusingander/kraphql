package com.github.lusingander.kraphql.test

import graphql.language.AstPrinter
import graphql.parser.Parser

fun formatQuery(rawQuery: String): String {
    val parser = Parser();
    val doc = parser.parseDocument(rawQuery);
    return AstPrinter.printAst(doc).trimEnd()
}