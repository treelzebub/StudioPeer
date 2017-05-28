package net.treelzebub.studiopeer.model

interface Track : Identifiable, Timestamped {

    /**
     * [Track]s may have different versions. For instance, there may be a few recordings of a
     * particular song, which [Member]s want to compare.
     */
    val otherVersionTrackIds: List<String>

    /**
     * The recording artist associated with this Track.
     */
    val artist: String

    /**
     * Title of this [Track].
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
     * Time at which this [Track] was last updated. Milliseconds since the epoch.
     */
    val lastUpdated: Long
}