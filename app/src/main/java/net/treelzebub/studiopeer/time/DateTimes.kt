package net.treelzebub.studiopeer.time

import com.instacart.library.truetime.TrueTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat

/**
 * Created by Tre Murillo on 5/27/17
 */
object DateTimes {

    val now: Long
        get() = TrueTime.now().time

    fun localTime(millis: Long? = null) = LocalTime(millis)

    fun printLocalTime(millis: Long): String {
        return DateTimeFormat.forPattern("hh:mm a").print(localTime(millis))
    }
}