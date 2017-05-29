package net.treelzebub.studiopeer.android.users

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.database.CollectionTypeIndicators
import net.treelzebub.studiopeer.database.Path
import net.treelzebub.studiopeer.database.Referenced
import net.treelzebub.studiopeer.database.StudioPeerDb


/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerUsers : Referenced {

    override val path = Path.of("users")
    override val reference = StudioPeerDb.reference(path)

    var mockUser: User? = null
    var user: User? = null //mockUser //?: FirebaseAuth.getInstance().currentUser?.let { /*StudioPeerUser.create(it)*/ }

    // TODO this is debug code...
    fun getAll() {
        StudioPeerDb.read(path, object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val users = data.getValue(CollectionTypeIndicators.getAllUsers)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

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
