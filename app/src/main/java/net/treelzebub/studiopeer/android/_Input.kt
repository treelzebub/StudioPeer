package net.treelzebub.studiopeer.android

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


/**
 * Created by Tre Murillo on 5/28/17
 */

fun Activity.dismissKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val focus =currentFocus ?: return
    focus.clearFocus()
    imm.hideSoftInputFromWindow(focus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}