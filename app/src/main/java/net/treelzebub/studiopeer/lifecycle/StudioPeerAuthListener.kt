package net.treelzebub.studiopeer.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.google.firebase.auth.FirebaseAuth
import net.treelzebub.studiopeer.activity.StudioPeerActivity
import net.treelzebub.studiopeer.auth.isAuthed
import net.treelzebub.studiopeer.contract.Intents

/**
 * Created by Tre Murillo on 5/27/17
 */
class StudioPeerAuthListener(
        private val a: StudioPeerActivity,
        private val lifecycle: Lifecycle,
        private val isSecure: Boolean,
        private val callback: () -> Unit
) : LifecycleObserver {

    private val auth = FirebaseAuth.getInstance()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        if (isSecure && !auth.isAuthed) {
            a.startActivity(Intents.welcome(a))
            a.finish()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {

    }
}