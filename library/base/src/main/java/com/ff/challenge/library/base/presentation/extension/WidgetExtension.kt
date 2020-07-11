package com.ff.challenge.library.base.presentation.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object WidgetExtension {

    fun EditText.onTextChanged(onTextChangedBody: ((text: String) -> Unit)? = null) {
        val listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // ignore
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // ignore
            }

            override fun afterTextChanged(s: Editable) {
                onTextChangedBody?.invoke(s.toString())
            }
        }
        addTextChangedListener(listener)
    }
}