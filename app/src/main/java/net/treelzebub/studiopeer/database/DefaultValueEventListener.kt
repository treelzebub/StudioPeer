package net.treelzebub.studiopeer.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

/**
 * Created by Tre Murillo on 5/28/17
 */

fun DatabaseReference.onDataChanged(fn: (DataSnapshot?) -> Unit) = DefaultValueEventListener(fn)

class DefaultValueEventListener(private val onDataChanged: (DataSnapshot?) -> Unit) : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot?) = onDataChanged(snapshot)

    override fun onCancelled(error: DatabaseError?) {
        DatabaseErrors.handle(error)
    }
}