package net.treelzebub.studiopeer

import android.app.Application

class StudioPeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StudioPeer.init(this)
    }
}