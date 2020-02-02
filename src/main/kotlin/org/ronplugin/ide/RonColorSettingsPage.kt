package org.ronplugin.ide

import org.ronplugin.RonFileType
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class RonColorSettingsPage : ColorSettingsPage {
    companion object {
        private val DESCRIPTORS = RonColor.values().map { it.attributesDescriptor }.toTypedArray()
    }

    override fun getHighlighter(): SyntaxHighlighter = RonSyntaxHighlighter()

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null

    override fun getIcon(): Icon? = RonFileType.INSTANCE.icon

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "Ron"

    override fun getDemoText(): String = """
    #![implicit_some(enable)]
    (
        boolean: true,
        float: 8.2,
        map: {
            1: '1',
            2: '4',
            3: '9',
            4: '1',
            5: '2',
            6: '3',
        },
        /*
          Block comment
        */
        nested: Nested(
            a: "Decode me!",
            b: 'z',
        ),
        // Line comment
        tuple: (3, 7),
    )
    """.trimIndent()
}