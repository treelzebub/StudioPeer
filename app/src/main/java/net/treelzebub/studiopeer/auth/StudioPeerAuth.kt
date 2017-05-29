package net.treelzebub.studiopeer.auth

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Tre Murillo on 3/11/17
 */

val FirebaseAuth.isAuthed: Boolean
    get() = currentUser != null

object StudioPeerAuth {

    private val auth = FirebaseAuth.getInstance()

    // TODO move login shit from MainActivity.

    fun listen(listener: FirebaseAuth.AuthStateListener) {
        auth.addAuthStateListener(listener)
    }

    fun unlisten(listener: FirebaseAuth.AuthStateListener) {
        auth.removeAuthStateListener(listener)
    }

    fun logOut() = auth.signOut()
}