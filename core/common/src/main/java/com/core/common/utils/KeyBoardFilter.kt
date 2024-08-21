package com.core.common.utils

import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils


class KeyBoardFilter : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int,
    ): CharSequence? {
        var keepOriginal = true
        val sb = StringBuilder(end - start)
        for (i in start until end) {
            val c = source[i]
            if (isCyrillicCharacter(c)) {
                sb.append(c)
            } else {
                keepOriginal = false
            }
        }
        if (keepOriginal) {
            return null
        } else {
            val sp = SpannableString(sb)
            if (source is Spanned) {
                TextUtils.copySpansFrom(source, start, sb.length, null, sp, 0)
            }
            return sb
            }
        }

    private fun isCyrillicCharacter(c: Char): Boolean {
        val block: Character.UnicodeBlock? = Character.UnicodeBlock.of(c)
        val isCyrillicCharacter =
            (block == Character.UnicodeBlock.CYRILLIC || block == Character.UnicodeBlock.CYRILLIC_SUPPLEMENTARY)
        return isCyrillicCharacter
    }
}