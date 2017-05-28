package net.treelzebub.studiopeer.android.prefs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import net.treelzebub.studiopeer.android.users.NoUserException
import net.treelzebub.studiopeer.android.users.StudioPeerUsers
import net.treelzebub.studiopeer.android.users.User
import org.joda.time.DateTime
import java.io.File


/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerPrefs {

    fun userPrefBoolean(key: String): SharedPref<Boolean> {
        return userPref(key, Boolean::class.java)
    }

    fun userPrefLong(key: String): SharedPref<Long> {
        return userPref(key, Long::class.java)
    }

    fun <T : Any> userPref(key: String, clazz: Class<T>): SharedPref<T> {
        return object : SharedPref<T>(key, clazz) {
            override fun getSharedPreferences(c: Context): SharedPreferences {
                return userPrefs(c)
            }
        }
    }

    fun basePrefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun baseEditor(context: Context): Editor {
        return basePrefs(context).edit()
    }


    fun getPrefs(context: Context, userPrefs: Boolean): SharedPreferences? {
        return if (userPrefs) userPrefs(context) else basePrefs(context)
    }

    fun userPrefs(context: Context): SharedPreferences {
        return StudioPeerUsers.user?.let {
            context.getSharedPreferences("" + it.uid, Context.MODE_PRIVATE)
        } ?: throw NoUserException("No User for userPrefs")
    }


    @JvmOverloads
    fun userPrefsNonNull(context: Context, user: User = StudioPeerUsers.user!!): SharedPreferences {
        return context.getSharedPreferences("" + user.uid, Context.MODE_PRIVATE)
    }

    fun userEditor(context: Context): Editor? {
        val pref = userPrefs(context)
        return pref.edit()
    }

    fun userEditorNonNull(context: Context): Editor {
        return userPrefsNonNull(context).edit()
    }

    fun withUser(context: Context, fn: (Editor) -> Unit) {
        val e = userEditor(context)
        if (e != null) fn(e)
    }

    fun deleteUserPrefs(context: Context, user: User): Boolean {
        return deletePrefs(context, "" + user.uid)
    }

    @SuppressLint("ApplySharedPref")
    private fun deletePrefs(context: Context, name: String): Boolean {
        val prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE) ?: return false
        prefs.edit().clear().commit()
        val root = context.filesDir ?: return false
        val dir = File(root.parent + "/shared_prefs/")
        val xml = File(dir, name + ".xml")
        return xml.delete()
    }

    fun getDateTime(prefs: SharedPreferences, key: String): DateTime? {
        val ts = prefs.getLong(key, -1)
        return if (ts == -1L) null else DateTime(ts)
    }

    fun putDateTime(editor: Editor, key: String, dt: DateTime?) {
        if (dt != null) {
            editor.putLong(key, dt.millis)
        } else {
            editor.remove(key)
        }
        editor.commit()
    }
}
