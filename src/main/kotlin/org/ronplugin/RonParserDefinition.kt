package org.ronplugin

import org.ronplugin.parser.RonParser
import org.ronplugin.psi.RonFile
import org.ronplugin.psi.RonTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class RonParserDefinition : ParserDefinition {

    companion object {
        val FILE = IFileElementType(RonLanguage.INSTANCE)
        val COMMENTS = TokenSet.create(RonTypes.COMMENT)
    }

    override fun createParser(project: Project): PsiParser = RonParser()

    override fun createFile(viewProvider: FileViewProvider): PsiFile = RonFile(viewProvider)

    override fun createLexer(project: Project?): Lexer = RonLexerAdapter()

    override fun createElement(node: ASTNode?): PsiElement = RonTypes.Factory.createElement(node)

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS
}