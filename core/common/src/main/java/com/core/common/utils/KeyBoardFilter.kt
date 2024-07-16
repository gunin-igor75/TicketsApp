package com.core.common.utils

import android.text.InputFilter
import android.text.Spanned

class KeyBoardFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int,
    ): CharSequence? {
        source?.let {
            for (i in start until end) {
                val word = Character.UnicodeBlock.of(it[i])
                word?.let {
                    val hasCyrillic = equals(Character.UnicodeBlock.CYRILLIC)
                    if (!hasCyrillic) {
                        return ""
                    }
                }
            }
        }
        return null
    }
}