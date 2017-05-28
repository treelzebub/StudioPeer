package net.treelzebub.studiopeer.model.entities

import net.treelzebub.studiopeer.model.DatabaseObject

interface Track : DatabaseObject {

    /**
     * [Track]s may have different versions. For instance, there may be a few recordings of a
     * particular song, which [Member]s want to compare.
     *
     * This is a Map of filename to title (for display). Since the filename is guaranteed unique,
     * it will serve as each recording's identifier.
     */
    var versions: Map<String, String>

    /**
     * The recording artist associated with this Track.
     */
    var artist: String

    /**
     * Title of this [Track].
     */
    var title: String

    /**
     * Position in sequence.
     */
    var number: Int

    /**
     * Length in milliseconds.
     */
    var length: Int

    /**
     * File size in bytes.
     */
    var size: Long
}