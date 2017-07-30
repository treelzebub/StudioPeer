package net.treelzebub.studiopeer.storage

import com.google.firebase.storage.StorageMetadata

/**
 * Created by Tre Murillo on 5/29/17
 */

fun StorageMetadata.Builder.put(pair: Pair<String, String>)
        = setCustomMetadata(pair.first, pair.second)

fun storageMetadata(fn: StorageMetadata.Builder.() -> Unit): StorageMetadata
        = StorageMetadata.Builder().apply(fn).build()
