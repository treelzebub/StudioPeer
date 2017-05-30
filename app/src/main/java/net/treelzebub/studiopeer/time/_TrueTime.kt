package net.treelzebub.studiopeer.time

import android.util.Log
import com.instacart.library.truetime.InvalidNtpServerResponseException
import com.instacart.library.truetime.TrueTime
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch

/**
 * Created by Tre Murillo on 5/28/17
 */


/**
 * Try 10 times to initialize [TrueTime]. According to the dev, it's "highly likely" that this will
 * throw an [InvalidNtpServerResponseException] a couple times before it succeeds. Mostly this is
 * an excuse to try out coroutines.
 *
 * https://github.com/instacart/truetime-android
 */
private var tries = 0
private const val TAG = "TrueTime"
fun TrueTime.initAsync() {
    launch(CommonPool) {
        Log.d(TAG, "init...")
        initialize()
    }.invokeOnCompletion {
        if (it != null) {
            Log.e(TAG, "init error", it)
            if (++tries > 9) throw it else initAsync()
        } else {
            tries = 0
            Log.d(TAG, "init successful: ${TrueTime.isInitialized()}")
            return@invokeOnCompletion
        }
    }
}
