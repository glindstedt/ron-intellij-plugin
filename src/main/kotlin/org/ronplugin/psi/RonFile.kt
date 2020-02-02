package org.ronplugin.psi

import org.ronplugin.RonFileType
import org.ronplugin.RonLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

class RonFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RonLanguage.INSTANCE) {

    override fun getFileType(): FileType = RonFileType.INSTANCE

    override fun toString(): String = "Ron file"
}