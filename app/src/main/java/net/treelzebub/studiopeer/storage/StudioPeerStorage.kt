package net.treelzebub.studiopeer.storage

import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import net.treelzebub.studiopeer.StudioPeer
import net.treelzebub.studiopeer.TAG
import java.io.File
import java.io.FileInputStream

object StudioPeerStorage {

    private val firebase = FirebaseStorage.getInstance()

    val HOME_DIR = "studio_peer/${StudioPeer.studioName}"

    // Returns a list of downloadUrls
//    fun ls(): List<String> {
//        reference.
//    }

    fun reference(filename: String): StorageReference {
        "asdf".reversed()
        return firebase.reference.child("$HOME_DIR/$filename")
    }

    fun upload(file: File, metadata: StorageMetadata?): UploadTask {
        return upload(file.name, file.toBytes()!!, metadata)
    }

    fun uploadStream(file: File, metadata: StorageMetadata?): UploadTask {
        return reference(file.name).putStream(FileInputStream(file))
    }

    /**
     * Overwrites without prompt
     */
    fun upload(filename: String, bytes: ByteArray, metadata: StorageMetadata?): UploadTask {
        val task = if (metadata != null) {
            reference(filename).putBytes(bytes, metadata)
        } else {
            reference(filename).putBytes(bytes)
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

    /**
     * Check FirebaseStorage for a file of this name.
     * @return the task that is reading the contents of the StorageReference.
     */
    fun checkExists(filename: String) { TODO() }


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