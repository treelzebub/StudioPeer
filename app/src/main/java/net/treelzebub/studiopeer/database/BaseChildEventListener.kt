package net.treelzebub.studiopeer.database

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

/**
 * Created by Tre Murillo on 5/28/17
 */
open class BaseChildEventListener : ChildEventListener {
    override fun onCancelled(error: DatabaseError) {}
    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {}
    override fun onChildRemoved(snapshot: DataSnapshot) {}
}
