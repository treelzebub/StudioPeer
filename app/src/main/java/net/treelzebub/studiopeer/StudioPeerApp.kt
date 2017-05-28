package net.treelzebub.studiopeer

import android.app.Application
import android.content.Context

class StudioPeerApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}