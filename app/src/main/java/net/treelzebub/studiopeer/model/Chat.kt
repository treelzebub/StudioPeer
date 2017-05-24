package net.treelzebub.studiopeer.model

/**
 * Created by Tre Murillo on 5/23/17
 *
 * Each Chat is associated with a [Track]. In the interest of keeping data structures flat, query
 * the "messages" child for [Message.id] equal to [Chat.id].
 */
interface Chat : Identifiable {

    /**
     * The unique identifier referring to this Chat. Satisfied by [Track.id].
     */
    override val id: String

    /**
     * The title of this Chat. Satisfied by [Track.title].
     */
    val title: String

    /**
     * The most recent message posted in the channel, or the empty String, if there are no [Message]s.
     */
    val lastMessage: CharSequence
        get() = ""

    /**
     * The time at which the [channel] was last updated. Updated on every [Message] push, satisfied
     * by Firebase Database. Milliseconds since the epoch.
     */
    val lastUpdated: Long
}