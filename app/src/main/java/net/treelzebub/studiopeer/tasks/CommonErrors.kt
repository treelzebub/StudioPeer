package net.treelzebub.studiopeer.tasks

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference

/**
 * Created by Tre Murillo on 7/30/17
 */

fun DatabaseReference.safeSetValue(obj: Any): Task<Void> {
    return setValue(obj).addOnCompleteListener {
        if (!it.isSuccessful) {
            Log.e("DatabaseReference", "Error updating ${it.exception?.message}", it.exception)
        }
    }
}