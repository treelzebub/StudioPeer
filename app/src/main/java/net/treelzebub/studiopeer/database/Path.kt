package net.treelzebub.studiopeer.database

import net.treelzebub.studiopeer.StudioPeer

/**
 * Created by Tre Murillo on 5/28/17
 *
 * Provides common directory structure for both FirebaseDatabase
 * and FirebaseStorage.
 */
object Path {

    const val ROOT_DIR  = "studio_peer"

    fun of(child: String) = "$ROOT_DIR/${StudioPeer.studioName}/$child"
}