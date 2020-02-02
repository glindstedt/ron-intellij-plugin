package org.ronplugin.ide

import org.ronplugin.RonLexerAdapter
import org.ronplugin.psi.RonTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as Default

class RonSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        val IDENTIFIER = createTextAttributesKey("RON_IDENTIFIER", Default.KEYWORD)
        val STRING = createTextAttributesKey("RON_STRING", Default.STRING)
        val NUMBER = createTextAttributesKey("RON_NUMBER", Default.NUMBER)
        val EXTENSION = createTextAttributesKey("RON_EXTENSION", Default.METADATA)
        val BLOCK_COMMENT = createTextAttributesKey("RON_BLOCK_COMMENT", Default.BLOCK_COMMENT)
        val LINE_COMMENT = createTextAttributesKey("RON_LINE_COMMENT", Default.LINE_COMMENT)

        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val STRING_KEYS = arrayOf(STRING)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val EXTENSION_KEYS = arrayOf(EXTENSION)
        private val BLOCK_COMMENT_KEYS = arrayOf(BLOCK_COMMENT)
        private val LINE_COMMENT_KEYS = arrayOf(LINE_COMMENT)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        when {
            RonTypes.ID == tokenType -> {
                return IDENTIFIER_KEYS
            }
            RonTypes.CHAR_LITERAL == tokenType || RonTypes.STRING_LITERAL == tokenType -> {
                return STRING_KEYS
            }
            RonTypes.FLOAT_LITERAL == tokenType || RonTypes.INTEGER_LITERAL == tokenType -> {
                return NUMBER_KEYS
            }
            RonTypes.EXTENSION == tokenType -> {
                return EXTENSION_KEYS
            }
            RonTypes.BLOCK_COMMENT == tokenType -> {
                return BLOCK_COMMENT_KEYS
            }
            RonTypes.LINE_COMMENT == tokenType -> {
                return LINE_COMMENT_KEYS
            }
            else -> return EMPTY_KEYS
        }
    }

    override fun getHighlightingLexer(): Lexer = RonLexerAdapter()
}