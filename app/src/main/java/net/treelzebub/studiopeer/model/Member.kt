package net.treelzebub.studiopeer.model

/**
 * Created by Tre Murillo on 5/23/17
 */
interface Member : Identifiable, Timestamped {

    /**
     * User's unique identifier. Supplied by FirebaseAuth. Read only.
     */
    override val id: String

    /**
     * Is this [Member] a Project Administrator? Administrators may modify this field.
     */
    val isAdmin: Boolean

    /**
     * [Project]s associated with this [Member]. Administrators may modify this field.
     */
    val projectIds: List<String>

    /**
     * [Member]'s name, for display. Read only.
     */
    val name: String

    /**
     * [Member]'s email address. Read only.
     */
    val email: String

    /**
     * [Member]'s avatar URL. Read only.
     */
    val avatarUrl: String
}