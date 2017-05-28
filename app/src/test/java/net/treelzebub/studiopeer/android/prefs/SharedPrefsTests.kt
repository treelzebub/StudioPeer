package net.treelzebub.studiopeer.android.prefs

import android.annotation.SuppressLint
import net.treelzebub.studiopeer.StudioPeer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Created by Tre Murillo on 5/28/17
 */
@RunWith(RobolectricTestRunner::class)
class SharedPrefsTests {

    private val baseDelegate = sharedPref<String>("foo", true)

    private var fooBasic: String? by baseDelegate
    private var fooNotNull: String by baseDelegate.notNull("bar")
    private var fooWriteback: String by baseDelegate.notNull("bar", true)

    @Test
    @SuppressLint("ApplySharedPref")
    fun testBasicTypes() {
        val prefs = StudioPeerPrefs.basePrefs(RuntimeEnvironment.application)
        val edit = prefs.edit()
        edit.clear().commit()

        edit.putGeneric("bool", true)
        edit.putGeneric("float", 1f)
        edit.putGeneric("int", 1)
        edit.putGeneric("long", 1L)
        edit.putGeneric("string", "foo")

        edit.commit()

        assertEquals(true, prefs.getGeneric<Boolean>("bool"))
        assertEquals(1f, prefs.getGeneric<Float>("float"))
        assertEquals(1, prefs.getGeneric<Int>("int"))
        assertEquals(1L, prefs.getGeneric<Long>("long"))
        assertEquals("foo", prefs.getGeneric<String>("string"))
    }

    @Test
    fun testDelegate() {
        StudioPeer.init(RuntimeEnvironment.application)
        assertEquals(null, fooBasic)
        assertEquals("bar", fooNotNull)
        assertEquals(null, fooBasic)
        assertEquals("bar", fooWriteback)
        assertEquals("bar", fooBasic)

        fooBasic = "baz"
        assertEquals("baz", fooWriteback)
        assertEquals("baz", fooBasic)
    }
}