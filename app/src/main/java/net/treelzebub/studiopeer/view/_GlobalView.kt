package net.treelzebub.studiopeer.view

import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewTreeObserver

/**
 * Created by Tre Murillo on 5/29/17
 */

private class SingleLayoutListener(
        private val view: View,
        private val fn: () -> Unit
) : ViewTreeObserver.OnGlobalLayoutListener {
    override fun onGlobalLayout() {
        view.viewTreeObserver?.removeOnGlobalLayoutListener(this)
        fn()
    }
}

fun View.onNextLayout(fn: () -> Unit) {
    viewTreeObserver!!.addOnGlobalLayoutListener(SingleLayoutListener(this, fn))
}

fun Activity.onNextLayout(fn: () -> Unit) {
    findViewById(android.R.id.content).onNextLayout(fn)
}

fun Fragment.onNextLayout(fn: () -> Unit) {
    view!!.onNextLayout(fn)
}

