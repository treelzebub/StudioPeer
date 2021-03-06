package net.treelzebub.studiopeer

import android.app.Application
import android.content.Context
import com.instacart.library.truetime.TrueTime
import net.danlew.android.joda.JodaTimeAndroid
import net.treelzebub.studiopeer.auth.StudioPeerAuth
import net.treelzebub.studiopeer.auth.StudioPeerAuthListener
import net.treelzebub.studiopeer.env.DevelopEnv
import net.treelzebub.studiopeer.env.StudioPeerEnv
import net.treelzebub.studiopeer.runtime.Stability
import net.treelzebub.studiopeer.time.initAsync

/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeer {

    /**
     * Set this before building for a specific client.
     */
    val studioName = "Develop"

    lateinit var context: Context
        private set

    private var isInit = false

    fun init(app: Application) {
        if (isInit) throw RuntimeException("StudioPeer has already been initialized.")
        isInit = true
        context = app
        Stability.onCrashLoop {
            StudioPeerAuth.logOut()
        }
        StudioPeerEnv.init(DevelopEnv(BuildConfig::class.java))
        if (!StudioPeerEnv.instance.isTest) {
            JodaTimeAndroid.init(app)
            TrueTime.build()
                    .withNtpHost("time.apple.com")
                    .withConnectionTimeout(30_000)
                    .withSharedPreferences(app)
                    .withLoggingEnabled(true)
                    .initAsync()
        }
        StudioPeerAuth.listen(StudioPeerAuthListener(app))

        // TODO init env
        // TODO init lifecycle
        // TODO if debug, init logger
    }
}