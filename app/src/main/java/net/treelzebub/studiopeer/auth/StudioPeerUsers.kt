package net.treelzebub.studiopeer.auth

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.database.StudioPeerDb
import net.treelzebub.studiopeer.model.Superusers

/**
 * Created by Tre Murillo on 3/11/17
 */
object StudioPeerUsers {

    private val db = StudioPeerDb

//    private val superusers: Superusers by lazy {
//        Log.d(TAG, "reading superusers")
//    }

    fun handleUserStatus(user: FirebaseUser) {
        val gson = GsonBuilder().setPrettyPrinting().create()
        StudioPeerDb.getSuperusers()
//        Log.d(TAG, gson.toJson(user))
//        if (user.email in superusers.emails) {
//            Log.d(TAG, "Superuser logged in.")
//        } else {
//            Log.d(TAG, "Regular user logged in.")
//        }
    }
}