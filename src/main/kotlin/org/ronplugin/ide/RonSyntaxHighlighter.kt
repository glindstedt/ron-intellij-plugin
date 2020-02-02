package org.ronplugin.ide

import org.ronplugin.RonLexerAdapter
import org.ronplugin.psi.RonTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

class RonSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {

        fun map(tokenType: IElementType?): RonColor? = when (tokenType) {
            RonTypes.ID -> RonColor.IDENTIFIER
            RonTypes.CHAR_LITERAL -> RonColor.STRING
            RonTypes.STRING_LITERAL -> RonColor.STRING
            RonTypes.FLOAT_LITERAL -> RonColor.NUMBER
            RonTypes.INTEGER_LITERAL -> RonColor.NUMBER
            RonTypes.EXTENSION -> RonColor.EXTENSION
            RonTypes.BLOCK_COMMENT -> RonColor.BLOCK_COMMENT
            RonTypes.LINE_COMMENT -> RonColor.LINE_COMMENT
            else -> null
        }
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = pack(map(tokenType)?.textAttributesKey)

    override fun getHighlightingLexer(): Lexer = RonLexerAdapter()
}