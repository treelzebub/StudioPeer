package net.treelzebub.studiopeer.database

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import net.treelzebub.studiopeer.model.DatabaseObject
import net.treelzebub.studiopeer.time.DateTimes


/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerDb {

    private val db = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference
        get() = db.reference

    fun <T : DatabaseObject> write(path: String, obj: T): Task<Void> {
        // TODO it'd be a whole lot cooler to have the db fill the lastUpdatedAt field, but then
        //      we'd have to read from the db after every write...hmmmmmm
        obj.lastUpdatedAt = DateTimes.now
        return reference.child(path).child(obj.id).setValue(obj)
    }

    fun read(path: String, listener: ValueEventListener) {
        reference.child(path).ref.addValueEventListener(listener)
    }

//    fun <T : Identifiable> findById(reference: String, child: String, id: String): T  {
//        return db.getReference(reference).child(child)....
//    }
}
