package net.treelzebub.studiopeer.storage.v2

import net.treelzebub.studiopeer.storage.storageMetadata
import java.io.File

/**
 * Created by Tre Murillo on 7/28/17
 */
data class RemoteFile(val path: String, val filename: String) {
    companion object {
        const val KEY_PATH = "path"
        const val KEY_FILENAME = "filename"
    }

    fun asMetadata() = storageMetadata {
        KEY_PATH to path
        KEY_FILENAME to filename
    }

    fun asFile() = File(path, filename)
}