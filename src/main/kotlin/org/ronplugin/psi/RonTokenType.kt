package org.ronplugin.psi

import org.ronplugin.RonLanguage
import com.intellij.psi.tree.IElementType

class RonTokenType(debugName: String) : IElementType(debugName, RonLanguage.INSTANCE) {

    override fun toString(): String {
        return "RonTokenType." + super.toString()
    }

}