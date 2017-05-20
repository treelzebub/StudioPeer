package net.treelzebub.studiopeer.auth

import com.google.firebase.auth.FirebaseAuth
import net.treelzebub.studiopeer.activity.isAuthed

/**
 * Created by Tre Murillo on 3/11/17
 */
object StudioPeerAuth {

    private val auth by lazy { FirebaseAuth.getInstance() }

    private val baseListener = FirebaseAuth.AuthStateListener {
        if (it.isAuthed) StudioPeerUsers.handleUserStatus(auth.currentUser!!)
    }

    fun listen(listener: FirebaseAuth.AuthStateListener? = null)
            = auth.addAuthStateListener(listener ?: baseListener)

    fun unlisten(listener: FirebaseAuth.AuthStateListener? = null)
            = auth.removeAuthStateListener(listener ?: baseListener)
}