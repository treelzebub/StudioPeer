package net.treelzebub.studiopeer.android.users

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerUsers {

    var mockUser: User? = null
    var user: User? = mockUser ?: FirebaseAuth.getInstance().currentUser?.let { StudioPeerUser.create(it) }
}

interface User {

    val uid: String
    val name: String
    val email: String
    // val token: String
}

data class StudioPeerUser(
        override val uid: String,
        override val name: String,
        override val email: String
): User {

    companion object {
        fun create(uid: String, name: String, email: String): User? {
            return StudioPeerUser(uid, name, email)
        }
        fun create(user: FirebaseUser): User? {
            return StudioPeerUser(user.uid, user.displayName!!, user.email!!)
        }
    }
}

class NoUserException(msg: String) : IllegalStateException(msg)