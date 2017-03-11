package net.treelzebub.studiopeer

import android.app.Application
import com.google.firebase.auth.FirebaseAuth

class StudioPeerApp : Application() {

//    val auth = FirebaseAuth()

    val listener by lazy {
        FirebaseAuth.AuthStateListener {
            it.currentUser?.let {
                // track whether the user is logged in or out
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun registerActivityLifecycleCallbacks(callback: ActivityLifecycleCallbacks?) {
        super.registerActivityLifecycleCallbacks(callback)
        // TODO
    }
}