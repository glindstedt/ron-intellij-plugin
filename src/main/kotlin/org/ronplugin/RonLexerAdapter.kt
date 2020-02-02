package org.ronplugin

import com.intellij.lexer.FlexAdapter

class RonLexerAdapter : FlexAdapter(RonLexer(null))