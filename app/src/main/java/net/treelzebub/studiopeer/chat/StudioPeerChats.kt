package net.treelzebub.studiopeer.chat

import com.google.android.gms.tasks.Task
import net.treelzebub.studiopeer.android.users.StudioPeerUsers
import net.treelzebub.studiopeer.database.Path
import net.treelzebub.studiopeer.database.Referenced
import net.treelzebub.studiopeer.database.StudioPeerDb
import net.treelzebub.studiopeer.model.impls.TextMessage
import net.treelzebub.studiopeer.tasks.safeSetValue
import net.treelzebub.studiopeer.time.DateTimes

/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerChats : Referenced {

    override val path = Path.of("chats")
    override val reference = StudioPeerDb.reference(path)

    fun send(trackId: String, chat: String): Task<Void> {
        val now = DateTimes.now
        val user = StudioPeerUsers.user!!
        val key = reference.child(trackId).push().key
        val message = TextMessage(key, user.id, now, now, trackId, chat, user.avatarUrl)
        return reference.child(trackId).child(key).safeSetValue(message)
    }
}
