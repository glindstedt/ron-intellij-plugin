package org.ronplugin.ide

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default

enum class RonColor(name: String, default: TextAttributesKey? = null) {
    IDENTIFIER("Variables//Identifier", Default.KEYWORD),
    STRING("Literals//String", Default.STRING),
    NUMBER("Literals//Number", Default.NUMBER),
    EXTENSION("Extension", Default.METADATA),
    BLOCK_COMMENT("Comments//Block Comment", Default.BLOCK_COMMENT),
    LINE_COMMENT("Comments//Line Comment", Default.LINE_COMMENT),
    DOT("Braces and Operators//Dot", Default.DOT),
    COMMA("Braces and Operators//Comma", Default.COMMA),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("org.ronplugin.$name", default)
    val attributesDescriptor = AttributesDescriptor(name, textAttributesKey)
}
