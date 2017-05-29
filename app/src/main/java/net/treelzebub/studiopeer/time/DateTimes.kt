package net.treelzebub.studiopeer.time

import com.instacart.library.truetime.TrueTime

/**
 * Created by Tre Murillo on 5/27/17
 */
object DateTimes {

    val now: Long
        get() = TrueTime.now().time
}