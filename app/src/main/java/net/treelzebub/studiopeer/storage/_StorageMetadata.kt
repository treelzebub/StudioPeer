package net.treelzebub.studiopeer.storage

import com.google.firebase.storage.StorageMetadata

/**
 * Created by Tre Murillo on 5/29/17
 */

fun <T : Any> storageMetadata(fn: StorageMetadata.() -> Unit): StorageMetadata = StorageMetadata().apply(fn)