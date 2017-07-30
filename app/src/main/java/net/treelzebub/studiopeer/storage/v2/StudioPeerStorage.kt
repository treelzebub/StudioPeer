package net.treelzebub.studiopeer.storage.v2

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.*
import net.treelzebub.studiopeer.database.StudioPeerDb

/**
 * Created by Tre Murillo on 7/28/17
 */
class StudioPeerStorage : RemoteStorage {

    private val firebase = FirebaseStorage.getInstance()

    override fun upload(remoteFile: RemoteFile, data: ByteArray): UploadTask {
        return getChild(remoteFile)
                .putBytes(data, remoteFile.asMetadata())
                .updateDatabaseOnComplete()
    }

    override fun download(remoteFile: RemoteFile): FileDownloadTask {
        return getChild(remoteFile)
                .getFile(remoteFile.asFile())
                .updateDatabaseOnComplete()
    }

    override fun stream(remoteFile: RemoteFile): StreamDownloadTask {
        return getChild(remoteFile)
                .stream
                .updateDatabaseOnComplete()
    }

    override fun delete(remoteFile: RemoteFile): Task<Void> {
        return getChild(remoteFile)
                .delete()
                .updateDatabaseOnComplete()
    }

    override fun ls(path: String): List<RemoteFile> {
        TODO()
    }

    override fun exists(remoteFile: RemoteFile) {
        TODO()
    }

    private fun getChild(metadata: RemoteFile): StorageReference {
        return firebase.reference.child(metadata.filename)
    }
}