package net.treelzebub.studiopeer.model

/**
 * Created by Tre Murillo on 5/27/17
 */
interface DatabaseObject : Identifiable, Timestamped {
    companion object {
        const val DEFAULT_STRING  = "-1"
        const val DEFAULT_LONG    = -1L
        const val DEFAULT_INT     = -1
        const val DEFAULT_BOOLEAN = false

        fun <T> defaultList()   = listOf<T>()
        fun <K, V> defaultMap() = mapOf<K, V>()
    }
}