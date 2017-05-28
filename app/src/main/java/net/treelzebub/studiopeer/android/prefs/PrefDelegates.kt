package net.treelzebub.studiopeer.android.prefs

import android.annotation.SuppressLint
import net.treelzebub.studiopeer.StudioPeer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Tre Murillo on 5/28/17
 */


inline fun <reified T: Any> sharedPref(key: String? = null, global: Boolean = false): PrefDelegate<T> {
    return PrefDelegate(T::class.java, key, global)
}

class PrefDelegate<T: Any>(private val cls: Class<T>, val key: String?, val global: Boolean) : ReadWriteProperty<Any, T?> {

    private val context = StudioPeer.context
    private val sharedPrefs = context.sharedPrefs()

    /**
     * By default, [notNull] will not update your prefs with [defaultValue].
     * If you desire that behavior, set [writeback] to [true]
     */
    fun notNull(defaultValue: T, writeback: Boolean = false): NotNullPrefDelegate<T> = NotNullPrefDelegate(this, defaultValue, writeback)

    private fun key(thisRef: Any, property: KProperty<*>): String {
        return key ?: "pref_${thisRef.javaClass.simpleName}_${property.name}"
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return sharedPrefs.getGeneric(key(thisRef, property), cls)
    }

    @SuppressLint("CommitPrefEdits")
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        sharedPrefs.edit().let {
            it.putGeneric(key(thisRef, property), value)
            it.commit()
        }
    }
}

class NotNullPrefDelegate<T: Any>(val delegate: PrefDelegate<T>, val defaultValue: T, val writeback: Boolean) : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        var retval = delegate.getValue(thisRef, property)
        if (retval == null) {
            retval = defaultValue
            if (writeback) {
                delegate.setValue(thisRef, property, retval)
            }
        }
        return retval
    }
    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) = delegate.setValue(thisRef, property, value)
}