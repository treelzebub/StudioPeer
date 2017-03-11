package net.treelzebub.studiopeer

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class StudioPeerApp : Application() {

    private val TAG = "StudioPeerApp"

    private val auth by lazy { FirebaseAuth.getInstance() }

    val listener = FirebaseAuth.AuthStateListener {
        it.currentUser?.let {
            Log.d(TAG, "User is authed")
        } ?: Log.d(TAG, "User not authed")
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStarted(activity: Activity) {
                auth.addAuthStateListener(listener)
            }

            override fun onActivityStopped(activity: Activity) {
                auth.removeAuthStateListener(listener)
            }

            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}
        })
    }
}