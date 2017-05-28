package net.treelzebub.studiopeer.contract

import android.content.Context
import net.treelzebub.studiopeer.activity.MainActivity
import org.jetbrains.anko.intentFor

/**
 * Created by Tre Murillo on 5/27/17
 */
object Intents {

    fun welcome(c: Context) = c.intentFor<MainActivity>()
}