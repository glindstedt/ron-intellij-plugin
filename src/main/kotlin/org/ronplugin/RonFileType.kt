package org.ronplugin

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.util.Icons
import javax.swing.Icon

class RonFileType : LanguageFileType(RonLanguage.INSTANCE) {
    companion object {
        @JvmStatic val INSTANCE = RonFileType()
    }

    override fun getIcon(): Icon? = Icons.FILE_ICON

    override fun getName(): String = "Ron file"

    override fun getDefaultExtension(): String = "ron"

    override fun getDescription(): String = "Rusty Object Notation file"
}