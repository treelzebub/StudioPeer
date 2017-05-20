package net.treelzebub.studiopeer.debug

import android.util.Log
import com.google.gson.GsonBuilder
import net.treelzebub.studiopeer.TAG

object StudioPeerGson {

    private val pretty = GsonBuilder().setPrettyPrinting().create()

    fun debugLog(tag: String, message: Any) {
        Log.d(TAG, pretty.toJson(message))
    }
}