package net.treelzebub.studiopeer.database

import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import net.treelzebub.studiopeer.model.DatabaseObject
import net.treelzebub.studiopeer.time.DateTimes

// Pass types that correspond to the available JSON types as follows:
//   String
//   Long
//   Double
//   Boolean
//   Map<String, Object>
//   List<Object>
//
// Pass a custom Java object, if the class that defines it has a default constructor
// that takes no arguments and has public getters for the properties to be assigned.
object StudioPeerDb {

    private const val KEY_SUPERUSERS = "superusers"

    private val db = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference
        get() = db.reference

    fun <T : DatabaseObject> write(path: String, obj: T, onComplete: () -> Unit): Task<Void> {
        // TODO it'd be a whole lot cooler to have the db fill the lastUpdatedAt field, but then
        //      we'd have to read from the db after every write...hmmmmmm
        obj.lastUpdatedAt = DateTimes.now
        return reference.child(path).child(obj.id).setValue(obj).addOnCompleteListener { onComplete() }
    }



//    fun <T : Identifiable> findById(reference: String, child: String, id: String): T  {
//        return db.getReference(reference).child(child)....
//    }

}

/*
function writeUserData(userId, name, email, imageUrl) {
    firebase.database().ref('users/' + userId).set({
        username: name,
        email: email,
        profile_picture : imageUrl
    });
}
*/