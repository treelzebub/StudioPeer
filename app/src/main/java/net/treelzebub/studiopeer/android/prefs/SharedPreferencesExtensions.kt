package net.treelzebub.studiopeer.android.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Tre Murillo on 5/28/17
 */


fun Context.sharedPrefs(): SharedPreferences {
    return PreferenceManager.getDefaultSharedPreferences(this)
            ?: throw IllegalStateException("SharePreferences is null.")
}

fun <T : Any> SharedPreferences.Editor.putGeneric(key: String, value: T?) {
    if (value == null) {
        remove(key)
        return
    }
    when (value) {
        is String  -> putString(key, value)
        is Long    -> putLong(key, value)
        is Int     -> putInt(key, value)
        is Float   -> putFloat(key, value)
        is Boolean -> putBoolean(key, value)
        else       -> throw IllegalArgumentException("Cannot handle data type.")
    }
}

inline fun <reified T : Any> SharedPreferences.getGeneric(key: String): T? {
    return getGeneric(key, T::class.java)
}

@Suppress("IMPLICIT_CAST_TO_ANY")
fun <T : Any> SharedPreferences.getGeneric(key: String, clazz: Class<T>): T? {
    if (!contains(key)) return null

    @Suppress("UNCHECKED_CAST")
    return when(clazz) {
        String::class.java, java.lang.String::class.java    -> getString(key, null)
        Long::class.java, java.lang.Long::class.java        -> getLong(key, 0L)
        Int::class.java, java.lang.Integer::class.java      -> getLong(key, 0L).toInt()
        Float::class.java, java.lang.Float::class.java      -> getFloat(key, 0f)
        Boolean::class.java, java.lang.Boolean::class.java  -> getBoolean(key, false)
        else                                                -> throw IllegalArgumentException("Cannot handle class: " + clazz)
    } as T
}