package net.treelzebub.studiopeer.model.impls

import com.google.firebase.database.IgnoreExtraProperties
import net.treelzebub.studiopeer.model.entities.Chat
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_LONG
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_STRING
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.defaultList

/**
 * Created by Tre Murillo on 5/27/17
 */

@IgnoreExtraProperties
data class StudioPeerChat(
        override var id: String                = DEFAULT_STRING,
        override var createdAt: Long           = DEFAULT_LONG,
        override var trackId: String           = DEFAULT_STRING,
        override var memberIds: List<String>   = defaultList(),
        override var lastUpdatedAt: Long       = DEFAULT_LONG,
        override var lastMessage: CharSequence = DEFAULT_STRING
) : Chat
