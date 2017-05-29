package net.treelzebub.studiopeer.view

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Created by Tre Murillo on 5/29/17
 */

fun EditText.onTextChanged(fn: (CharSequence?, Int, Int, Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            fn(s, start, before, count)
        }
    })
}