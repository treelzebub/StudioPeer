package net.treelzebub.studiopeer.activity

import android.arch.lifecycle.LifecycleActivity
import net.treelzebub.studiopeer.lifecycle.StudioPeerAuthListener

abstract class StudioPeerActivity : LifecycleActivity() {
    abstract val isSecure: Boolean
    abstract val authListener: StudioPeerAuthListener
}