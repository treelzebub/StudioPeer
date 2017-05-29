package net.treelzebub.studiopeer.android.users

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.IgnoreExtraProperties
import net.treelzebub.studiopeer.time.DateTimes
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 5/28/17
 */
@IgnoreExtraProperties()
class StudioPeerUser() : User {

    companion object {
        fun create(user: FirebaseUser): StudioPeerUser {
            val now = DateTimes.now
            return StudioPeerUser(user.uid, now, now, user.displayName!!, user.email!!, user.photoUrl.toString())
        }
    }

    constructor(id: String, createdAt: Long, lastUpdatedAt: Long, name: String, email: String, avatarUrl: String) : this() {
        this.id = id
        this.createdAt = createdAt
        this.lastUpdatedAt = lastUpdatedAt
        this.name = name
        this.email = email
        this.avatarUrl = avatarUrl
    }

    override lateinit var id: String
    override var createdAt: Long by Delegates.notNull()
    override var lastUpdatedAt: Long by Delegates.notNull()
    override lateinit var name: String
    override lateinit var email: String
    override lateinit var avatarUrl: String
}