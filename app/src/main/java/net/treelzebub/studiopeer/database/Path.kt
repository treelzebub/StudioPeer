package net.treelzebub.studiopeer.database

import net.treelzebub.studiopeer.StudioPeer

/**
 * Created by Tre Murillo on 5/28/17
 */
object Path {
    fun of(child: String) = "${StudioPeer.studioName}/$child"
}