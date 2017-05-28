package net.treelzebub.studiopeer.model.entities

import net.treelzebub.studiopeer.model.DatabaseObject

interface Track : DatabaseObject {

    /**
     * TODO
     * [Track]s may have different versions. For instance, there may be a few recordings of a
     * particular song, which [Member]s want to compare.
     */
//    var otherVersionTrackIds: List<String>
    /**
     * TODO
     * If [otherVersionTrackIds] is empty, this is `true`. Otherwise, only one
     */
//    var isPrimaryVersion: Boolean


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