package net.treelzebub.studiopeer.database

import android.util.Log
import com.google.firebase.database.DatabaseError
import net.treelzebub.studiopeer.TAG

/**
 * Created by Tre Murillo on 5/28/17
 */
object DatabaseErrors {

    fun handle(error: DatabaseError?) {
        // TODO
        Log.e(TAG, error?.message ?: "No message")
        Log.e(TAG, error?.details ?: "No details")
        throw error?.toException() ?: RuntimeException("Unhandled exception.")
    }
}