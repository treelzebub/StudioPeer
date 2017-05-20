package net.treelzebub.studiopeer.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import net.treelzebub.studiopeer.auth.StudioPeerAuth

open class StudioPeerActivity : FragmentActivity {

    constructor() : this(true)

    constructor(isSecure: Boolean) : super() {
        this.isSecure = isSecure
    }

    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    val isSecure: Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        StudioPeerAuth.listen()
        if (isSecure && !auth.isAuthed) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        StudioPeerAuth.unlisten()
    }

    override fun onDestroy() {
        super.onDestroy()
        StudioPeerAuth.unlisten()
    }
}