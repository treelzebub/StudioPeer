package net.treelzebub.studiopeer.model

/**
 * Created by Tre Murillo on 5/27/17
 *
 * A [Project] represents a recording session, administered by a recording engineer, associated with
 * recording artists. Engineer [Member]s are Administrators, and can add/remove [Track]s, and invite
 * artist [Member]s to the [Project].
 */
interface Project : Identifiable, Timestamped {

    /**
     * Name of this [Project], for display.
     */
    val name: String

    /**
     * IDs of [Member]s associated with this [Project]. Upon app launch, StudioPeer queries the
     * database for [Member.projectIds] matching this [Project].
     */
    val memberIds: List<String>

    /**
     * IDs of Administrator [Member]s associated with this [Project].
     */
    val adminIds: List<String>

    /**
     * IDs of [Track]s associated with this [Project].
     */
    val trackIds: List<String>

}