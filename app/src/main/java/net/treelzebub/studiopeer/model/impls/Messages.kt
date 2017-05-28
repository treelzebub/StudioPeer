package net.treelzebub.studiopeer.model.impls

import com.google.firebase.database.IgnoreExtraProperties
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_LONG
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_STRING
import net.treelzebub.studiopeer.model.entities.Message


/**
 * Created by Tre Murillo on 5/27/17
 */

@IgnoreExtraProperties
data class TextMessage(
        override var id: String          = DEFAULT_STRING,
        override var userId: String      = DEFAULT_STRING,
        override var createdAt: Long     = DEFAULT_LONG,
        override var lastUpdatedAt: Long = DEFAULT_LONG,
        override var chatId: String      = DEFAULT_STRING,
        /**
         * Text content entered by the user. A regular ol' chat message.
         */
        var text: CharSequence           = DEFAULT_STRING
) : Message

// https://firebase.google.com/docs/storage/android/download-files
@IgnoreExtraProperties
data class AttachmentMessage(
        override var id: String          = DEFAULT_STRING,
        override var userId: String      = DEFAULT_STRING,
        override var createdAt: Long     = DEFAULT_LONG,
        override var lastUpdatedAt: Long = DEFAULT_LONG,
        override var chatId: String      = DEFAULT_STRING,
        /**
         * The directory in Firebase Storage.
         */
        val dir: String                  = DEFAULT_STRING,

        /**
         * The name of the file, which must exist in [dir].
         */
        val filename: String             = DEFAULT_STRING
) : Message {
    /**
     * Pass this to StorageReference.child(), then call getFile().
     */
    val child: String
        get() = "$dir/$filename"
}