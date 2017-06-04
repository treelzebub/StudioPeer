package net.treelzebub.studiopeer.storage

import android.util.Log
import net.treelzebub.studiopeer.TAG
import java.io.*


/**
 * Created by Tre Murillo on 5/29/17
 */

fun File.toBytes(): ByteArray? {
    return try {
        FileInputStream(this).use { it.readBytes() }
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun Serializable.toByteArray(): ByteArray? {
    ByteArrayOutputStream().use {
        bos ->
        return try {
            ObjectOutputStream(bos).use {
                oos ->
                oos.writeObject(this)
                oos.flush()
                bos.toByteArray()
            }
        } catch (e: IOException) {
            Log.e(TAG, "", e)
            null
        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> ByteArray.toSerializable(): T? {
    ByteArrayInputStream(this).use {
        bis ->
        return try {
            ObjectInputStream(bis).use {
                ois ->
                ois.readObject() as T
            }
        } catch (e: IOException) {
            Log.e(TAG, "", e)
            null
        }
    }
}