package net.treelzebub.studiopeer.storage

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.TAG

// assumes authed user... TODO
object StudioPeerStorage {


    private val storage = FirebaseStorage.getInstance()

    private fun home(c: Context) = c.getString(R.string.studio_peer_home_directory)

    fun reference(c: Context, filename: String): StorageReference {
        return storage.reference.child("${home(c)}/$filename")
    }

    /**
     * Overwrites without prompt
     */
    fun upload(c: Context, filename: String, bytes: ByteArray, metadata: StorageMetadata?): UploadTask {
        val task = if (metadata != null) {
            reference(c, filename).putBytes(bytes, metadata)
        } else {
            reference(c, filename).putBytes(bytes)
        }
        return task.apply {
            addOnSuccessListener {
                Log.d(TAG, "Success! URL: ${it!!.downloadUrl!!}")
            }.addOnFailureListener {
                Log.d(TAG, "Fail! URL: ${it.message}")
            }
        }
        // OnProgressListener
        //
        // Pause the upload
        //    uploadTask.pause();
        // Resume the upload
        //    uploadTask.resume();
        // Cancel the upload
        //    uploadTask.cancel();
    }

    // Example code:
//    val storage = FirebaseStorage.getInstance()
//    val debugByteArray = byteArrayOf(1, 0, 3, 3, 71, 0x12, 0x79)
//    val storageRef = storage.reference
//    val fileRef = storageRef.child("my-bytes")
//    val metadata = StorageMetadata.Builder().setCustomMetadata("mine", "yes!").build()
//    val uploadTask = fileRef.putBytes(debugByteArray, metadata)
//    uploadTask.addOnFailureListener {
//     //    Handle unsuccessful uploads
//    }.addOnSuccessListener {
//     //    taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//        taskSnapshot ->
//        Log.d(TAG, "Bytes: "+taskSnapshot!!.metadata!!.sizeBytes + " " + taskSnapshot.downloadUrl.toString())
//    }
}