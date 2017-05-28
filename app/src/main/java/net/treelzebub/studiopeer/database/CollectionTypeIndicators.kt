package net.treelzebub.studiopeer.database

import com.google.firebase.database.GenericTypeIndicator
import net.treelzebub.studiopeer.android.users.StudioPeerUser


/**
 * Created by Tre Murillo on 5/28/17
 * 
 * @see[https://stackoverflow.com/questions/42546959/kotlin-cant-use-generictypeindicator-to-call-firebase-databases-getvalue]
 */

typealias UsersMap = Map<String, @JvmSuppressWildcards StudioPeerUser>

object CollectionTypeIndicators {

    val getAllUsers = object : GenericTypeIndicator<UsersMap>() {}
}