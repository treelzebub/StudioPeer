package net.treelzebub.studiopeer.storage.v2

import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.StreamDownloadTask
import com.google.firebase.storage.UploadTask

/**
 * Created by Tre Murillo on 7/28/17
 */
internal fun UploadTask.updateDatabaseOnComplete() = apply {
    TODO()
}

internal fun FileDownloadTask.updateDatabaseOnComplete() = apply {
    TODO()
}

internal fun StreamDownloadTask.updateDatabaseOnComplete() = apply {
    TODO()
}

internal fun Task<Void>.updateDatabaseOnComplete() = apply {
    TODO()
}