package org.ronplugin.psi

import org.ronplugin.RonLanguage
import com.intellij.psi.tree.IElementType

class RonElementType(debugName: String) : IElementType(debugName, RonLanguage.INSTANCE)