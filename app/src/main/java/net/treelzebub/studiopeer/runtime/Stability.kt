package net.treelzebub.studiopeer.runtime

import android.os.Handler
import android.util.Log
import net.treelzebub.knapsack.extensions.TAG
import net.treelzebub.studiopeer.android.prefs.sharedPref

/**
 * Created by Tre Murillo on 5/29/17
 */
object Stability {

    private var crashCount by sharedPref<Int>("stability_crash_count", true)

    /**
     * Detect a crash loop by setting a bit, then clearing it at some point in the future.
     * If a crash happens in that time, we'll know because the bit is still set.
     *
     * @param threshold How many crashes until we pull the plug?
     * @param safeAfterMs How many milliseconds before we clear the bit?
     * @param fn the block to execute on crash loop detection
     *
     * @return true if crash was detected, else false
     */
    @Synchronized
    @JvmStatic
    @JvmOverloads
    fun onCrashLoop(threshold:Int = 2, safeAfterMs: Long = 2500L, fn: () -> Unit): Boolean {
        val tag = TAG

        /**
         * In [safeAfterMs] seconds, if we're still alive, reset the crashCount to null.
         */
        Handler().postDelayed({
            Log.d(tag, "No Crash")
            crashCount = null
        }, safeAfterMs)

        /**
         * If crashes weren't nulled out, then something went wrong. Increment the counter.
         * Or just set counter to non-null so that any subsequent crashes will be picked up on next launch.
         */
        crashCount = ((crashCount?:-1) + 1).apply {
            Log.d(tag, "Crash Count: $this")
        }

        /**
         * If we've incremented [threshold] times, launch the nukes.
         */
        if (crashCount?:0 >= threshold) {
            Log.w(tag, "Crash Loop Detected.")
            fn()
            return true
        }
        return false
    }
}