package net.treelzebub.studiopeer.database

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.debug.StudioPeerGson
import java.lang.Thread.sleep

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

    fun getSuperusers() {
        val superusers = db.getReference(KEY_SUPERUSERS)!!
        superusers.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d(TAG, snapshot.toString())
            }
            override fun onCancelled(error: DatabaseError) {
                StudioPeerGson.debugLog(TAG, error)
                throw IllegalStateException()
            }
        })
        // TODO this looks pretty cool:
        //    https://github.com/nmoskalenko/RxFirebase
    }

    //            FirebaseAuth.getInstance().currentUser?.let {
//                // Write a message to the database
//                val database = FirebaseDatabase.getInstance()
//                val myRef = database.getReference("something") // TODO hold in state object
//                myRef.setValue(SomeThing())
//
//                // Read from the database
//                myRef.addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//                        val value = dataSnapshot.getValue(ByteArray::class.java)
//                        Log.d(TAG, "Value is not null: value != null")
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException())
//                    }
//                })
//            }
}