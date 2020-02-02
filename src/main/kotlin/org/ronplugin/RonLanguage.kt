package org.ronplugin

import com.intellij.lang.Language

class RonLanguage : Language("Ron") {
   companion object {
      @JvmStatic val INSTANCE = RonLanguage()
   }
}