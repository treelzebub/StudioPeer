package net.treelzebub.studiopeer

import android.app.Application
import android.content.Context
import net.danlew.android.joda.JodaTimeAndroid
import net.treelzebub.studiopeer.auth.StudioPeerAuth
import net.treelzebub.studiopeer.env.DevelopEnv
import net.treelzebub.studiopeer.env.StudioPeerEnv
import net.treelzebub.studiopeer.lifecycle.StudioPeerAuthListener

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
        StudioPeerEnv.init(DevelopEnv(BuildConfig::class.java))
        JodaTimeAndroid.init(app) // TODO if if (Env.instance.testFramework != Robolectric) {
        StudioPeerAuth.listen(StudioPeerAuthListener(app))

        // TODO init env
        // TODO init lifecycle
        // TODO if debug, init logger
    }
}