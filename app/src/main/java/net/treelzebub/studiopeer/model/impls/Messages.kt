package net.treelzebub.studiopeer.model.impls

import net.treelzebub.studiopeer.model.entities.Message
import kotlin.properties.Delegates


/**
 * Created by Tre Murillo on 5/27/17
 */

class TextMessage() : Message {

    constructor(id: String, userId: String, createdAt: Long, lastUpdatedAt: Long, chatId: String, text: String, avatarUrl: String) : this() {
        this.id = id
        this.userId = userId
        this.createdAt = createdAt
        this.lastUpdatedAt = lastUpdatedAt
        this.chatId = chatId
        this.text = text
        this.avatarUrl = avatarUrl
    }

    override lateinit var id: String
    override lateinit var userId: String
    override var createdAt: Long by Delegates.notNull()
    override var lastUpdatedAt: Long by Delegates.notNull()
    override lateinit var chatId: String
    override lateinit var avatarUrl: String

    /**
     * Text content entered by the user. A regular ol' chat message.
     */
    lateinit var text: String
}
//
//// https://firebase.google.com/docs/storage/android/download-files
//data class AttachmentMessage(
//        override var id: String          = DEFAULT_STRING,
//        override var userId: String      = DEFAULT_STRING,
//        override var createdAt: Long     = DEFAULT_LONG,
//        override var lastUpdatedAt: Long = DEFAULT_LONG,
//        override var chatId: String      = DEFAULT_STRING,
//        /**
//         * The directory in Firebase Storage.
//         */
//        val dir: String                  = DEFAULT_STRING,
//
//        /**
//         * The name of the file, which must exist in [dir].
//         */
//        val filename: String             = DEFAULT_STRING
//) : Message {
//    /**
//     * Pass this to [StudioPeerDb]'s CRUD as the path argument, then call getFile().
//     */
//    val path: String
//        get() = "$dir/$filename"
//}