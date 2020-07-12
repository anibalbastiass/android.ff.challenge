package com.ff.challenge.library.base.presentation.extension

import java.text.Normalizer

object StringExtension {

    fun removeDiacriticalMarks(string: String): String {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+", "")
    }

}