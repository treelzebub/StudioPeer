package net.treelzebub.studiopeer.model.entities

import net.treelzebub.studiopeer.model.DatabaseObject


/**
 * Created by Tre Murillo on 5/23/17
 */
interface Message : DatabaseObject {

    /**
     * Messages are grouped by [Chat] objects,
     * This is the same as the associated [Track.id].
     */
    val chatId: String

    /**
     * The ID of the user who sent this Message. Supplied by FirebaseUser.
     */
    val userId: String
}
