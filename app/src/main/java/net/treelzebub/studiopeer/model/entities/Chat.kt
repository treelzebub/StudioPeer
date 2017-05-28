package net.treelzebub.studiopeer.model.entities

import net.treelzebub.studiopeer.model.DatabaseObject


/**
 * Created by Tre Murillo on 5/23/17
 *
 * Each Chat is associated with a [Track]. In the interest of keeping data structures flat, query
 * the "messages" child for [Message.id] equal to [Chat.id].
 */
interface Chat : DatabaseObject {

    /**
     * The time at which the [channel] was last updated. Updated on every [Message] push, satisfied
     * by FirebaseDatabase. Milliseconds since the epoch.
     */
    override var lastUpdatedAt: Long

    /**
     * ID of the [Track] associated with this [Chat]. The title of this [Track] is used as the
     * title of this [Chat].
     */
    var trackId: String

    /**
     * IDs of [Member]s associated with this [Chat].
     */
    var memberIds: List<String>

    /**
     * The most recent message posted in the channel, or the empty String, if there are no [Message]s.
     */
    var lastMessage: String
}
