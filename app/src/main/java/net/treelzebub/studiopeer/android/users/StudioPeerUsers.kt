package net.treelzebub.studiopeer.android.users

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.database.StudioPeerDb

/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerUsers {

    private const val path = "users"

    var mockUser: User? = null
    var user: User? = null //mockUser //?: FirebaseAuth.getInstance().currentUser?.let { /*StudioPeerUser.create(it)*/ }

    fun onLogIn(user: FirebaseUser): Task<Void>? {
        if (this.user != null) {
            Log.d(TAG, "User ${this.user!!.id} already logged in.")
            return null
        }
        this.user = StudioPeerUser.create(user)
        Log.d(TAG, "User logged in: ${user.displayName} (${user.email})")
        return write(this.user!!)
    }

    private fun write(user: User): Task<Void> = StudioPeerDb.write(path, user)
}
