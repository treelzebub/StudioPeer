package net.treelzebub.studiopeer.database

import android.util.Log
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import net.treelzebub.studiopeer.StudioPeer
import net.treelzebub.studiopeer.TAG
import org.jetbrains.anko.toast

/**
 * Created by Tre Murillo on 5/28/17
 */
object DatabaseErrors {

    fun handle(error: DatabaseError?) {
        // TODO
        Log.e(TAG, error?.message ?: "No message")
        Log.e(TAG, error?.details ?: "No details")
        if (error?.toException() is DatabaseException) {
            StudioPeer.context.get()?.toast("Database exception.")
            return
        }
        throw error?.toException() ?: RuntimeException("Unhandled exception.")
    }
}