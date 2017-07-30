package net.treelzebub.studiopeer.storage

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import net.treelzebub.studiopeer.StudioPeer
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.database.Path
import net.treelzebub.studiopeer.database.StudioPeerDb
import net.treelzebub.studiopeer.storage.v2.RemoteFile
import java.io.File
import java.io.FileInputStream


// Example:
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
object StudioPeerStorage {

    private val storage = FirebaseStorage.getInstance()

    private fun reference(filename: String): StorageReference {
        return storage.reference.child(Path.of(filename))
    }

    // Returns a list of downloadUrls
    fun ls(): List<String> {
        TODO()
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
    }

    fun upload(file: File, metadata: StorageMetadata?): UploadTask {
        return upload(file.name, file.toBytes()!!, metadata)
    }

    fun uploadStream(file: File, metadata: StorageMetadata?): UploadTask {
        return reference(file.name).putStream(FileInputStream(file))
    }

    fun updateListing(child: String, remoteFile: RemoteFile): Task<Void> {
        StudioPeerDb.write(Path.of(filename), remoteFile)
    }

    /**
     * Check FirebaseStorage for a file of this name.
     * @return the task that is reading the contents of the StorageReference.
     */
    fun checkExists(filename: String) { TODO() }
}