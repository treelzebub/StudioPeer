package net.treelzebub.studiopeer.model

interface Track : Identifiable {

    /**
     * A unique identifier
     */
    override val id: String

    /**
     * The recording artist associated with this Track.
     */
    val artist: String

    /**
     * Title of the Track.
     */
    val title: String

    /**
     * Position in sequence.
     */
    val number: Int

    /**
     * Length in milliseconds.
     */
    val length: Int

    /**
     * File size in bytes.
     */
    val size: Long

    /**
     * Time at which this was last updated, in UTC milliseconds since the epoch.
     */
    val lastUpdated: Long
}