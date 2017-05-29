package net.treelzebub.studiopeer.auth

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.android.users.StudioPeerUsers
import net.treelzebub.studiopeer.contract.Intents


/**
 * Created by Tre Murillo on 5/27/17
 */
class StudioPeerAuthListener(private val c: Context) : FirebaseAuth.AuthStateListener {

    override fun onAuthStateChanged(auth: FirebaseAuth) {
        try {
            checkReauth(auth)
        } catch (e: Exception) {
            Log.e(TAG, "Caught Exception", e)
            login()
        }
    }

    private fun checkReauth(auth: FirebaseAuth) {
        if (auth.isAuthed) {
            StudioPeerUsers.onLogIn(auth.currentUser!!)?.addOnCompleteListener {
                Log.d(TAG, "Successfully wrote user: ${auth.currentUser!!.uid}")
            }
        } else login()
    }

    private fun login() = c.startActivity(Intents.welcome(c, true))
}
