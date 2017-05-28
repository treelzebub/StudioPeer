package net.treelzebub.studiopeer.contract

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import net.treelzebub.studiopeer.activity.MainActivity
import org.jetbrains.anko.intentFor

/**
 * Created by Tre Murillo on 5/27/17
 */
object Intents {

    fun welcome(c: Context, isUnauthed: Boolean): Intent {
        return c.intentFor<MainActivity>().apply {
            if (isUnauthed) {
                flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            }
        }
    }
}