package net.treelzebub.studiopeer.auth

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Tre Murillo on 5/28/17
 */
object AuthState {

    val isAuthed: Boolean get() = FirebaseAuth.getInstance().isAuthed
    private var willLogout = false
}