package net.treelzebub.studiopeer.lifecycle

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.android.users.StudioPeerUsers
import net.treelzebub.studiopeer.auth.isAuthed
import net.treelzebub.studiopeer.contract.Intents

/**
 * Created by Tre Murillo on 5/27/17
 */
class StudioPeerAuthListener(private val c: Context) : FirebaseAuth.AuthStateListener {

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        if (auth.isAuthed) {
            StudioPeerUsers.onLogIn(auth.currentUser!!)?.addOnCompleteListener {
                Log.d(TAG, "Database write complete for user: ${auth.currentUser!!.uid}")
            }
        } else {
            c.startActivity(Intents.welcome(c, true))
        }
    }
}
