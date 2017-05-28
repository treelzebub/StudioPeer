package net.treelzebub.studiopeer.android.prefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


/**
 * Created by Tre Murillo on 5/28/17
 */
open class SharedPref<T : Any>(val key: String, private val clazz: Class<T>) {

    private var value: T?  = null
    private var hasChanged = false

    open fun getSharedPreferences(c: Context): SharedPreferences {
        return c.sharedPrefs()
    }

    fun get(c: Context, defaultValue: T? = null): T? {
        return if (hasChanged) {
            value
        } else {
            getSharedPreferences(c).getGeneric(key, clazz) ?: defaultValue
        }
    }

    @SuppressLint("CommitPrefEdits")
    fun put(c: Context, value: T?, commit: Boolean) {
        hasChanged = !commit
        this.value = if (hasChanged) value else null
        getSharedPreferences(c).edit().putGeneric<Any>(key, value)
        if (commit) commit(c)
    }

    @SuppressLint("ApplySharedPref")
    fun commit(c: Context) {
        getSharedPreferences(c).edit().commit()
        hasChanged = false
        value = null
    }

    fun remove(c: Context, commit: Boolean) = put(c, null, commit)

    fun revert(c: Context) {
        hasChanged = false
        value = null
    }
}