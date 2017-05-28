package net.treelzebub.studiopeer.model

/**
 * Created by Tre Murillo on 5/27/17
 *
 *
 */

interface Timestamped {
    val createdAt: Long
    var lastUpdatedAt: Long

    /**
     * Update [lastUpdatedAt] and commit the change to your data store.
     */
    fun update(time: Long)
}
