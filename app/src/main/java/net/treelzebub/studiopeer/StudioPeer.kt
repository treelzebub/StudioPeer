package net.treelzebub.studiopeer

import android.app.Application
import android.content.Context
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeer {

    lateinit var context: Context
        private set

    private var isInit = false

    fun init(app: Application) {
        if (isInit) throw RuntimeException("StudioPeer has already been initialized.")
        isInit = true
        context = app
        // TODO init env
        // TODO init lifecycle
        JodaTimeAndroid.init(app) // TODO if if (Env.instance.testFramework != Robolectric) {
        // TODO if debug, init logger
    }

}