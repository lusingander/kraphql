package com.github.lusingander.kraphql.kotlin

enum class Keywords(private val word: String) {
    // https://kotlinlang.org/docs/reference/keyword-reference.html

    AS("as"),
    BREAK("break"),
    CLASS("class"),
    CONTINUE("continue"),
    DO("do"),
    ELSE("else"),
    FALSE("false"),
    FOR("for"),
    FUN("fun"),
    IF("if"),
    IN("in"),
    INTERFACE("interface"),
    IS("is"),
    NULL("null"),
    OBJECT("object"),
    PACKAGE("package"),
    RETURN("return"),
    SUPER("super"),
    THIS("this"),
    THROW("throw"),
    TRUE("true"),
    TRY("try"),
    TYPEALIAS("typealias"),
    TYPEOF("typeof"),
    VAL("val"),
    VAR("var"),
    WHEN("when"),
    WHILE("while"),
    ;

    companion object {
        fun keywords(): Set<String> = Keywords.values().map { it.word }.toSet()
    }
}

fun String.isKotlinKeyword(): Boolean = this in Keywords.keywords()

fun String.backquote(): String = if (this.isKotlinKeyword()) "`$this`" else this