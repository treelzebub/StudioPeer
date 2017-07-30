package net.treelzebub.studiopeer.storage.v2

import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.StreamDownloadTask
import com.google.firebase.storage.UploadTask

/**
 * Created by Tre Murillo on 7/28/17
 */
interface RemoteStorage {

    fun upload(remoteFile: RemoteFile, data: ByteArray): UploadTask

    fun download(remoteFile: RemoteFile): FileDownloadTask

    fun stream(remoteFile: RemoteFile): StreamDownloadTask

    fun delete(remoteFile: RemoteFile): Task<Void>

    fun ls(path: String): List<RemoteFile>

    fun exists(remoteFile: RemoteFile): Boolean
}