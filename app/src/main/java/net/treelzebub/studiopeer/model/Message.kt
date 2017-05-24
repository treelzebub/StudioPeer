package net.treelzebub.studiopeer.model


/**
 * Created by Tre Murillo on 5/23/17
 */
interface Message : Identifiable {

    /**
     * A unique identifier referring to the Message instance.
     */
    override val id: String

    /**
     * The time at which the server received this Message. Supplied by Firebase Database.
     * Milliseconds since the epoch.
     */
    val timestamp: Long

    /**
     * Messages are grouped by [Chat] objects,
     * This is the same as the associated [Track.id].
     */
    val chatId: String

    /**
     * The user who sent this Message. Supplied by FirebaseUser's email field.
     */
    val username: String
}

interface TextMessage : Message {

    /**
     * Text content entered by the user. A regular ol' chat message.
     */
    val text: CharSequence
}

// https://firebase.google.com/docs/storage/android/download-files
interface AttachmentMessage : Message {

    /**
     * The directory in Firebase Storage.
     */
    val dir: String

    /**
     * The name of the file, which must exist in [dir].
     */
    val filename: String

    /**
     * Pass this to StorageReference.child(), then call getFile().
     */
    val child: String
        get() = "$dir/$filename"
}