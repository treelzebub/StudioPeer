package net.treelzebub.studiopeer.model.impls

import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_LONG
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_STRING
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.defaultList
import net.treelzebub.studiopeer.model.entities.Chat

/**
 * Created by Tre Murillo on 5/27/17
 */
data class StudioPeerChat(
        override var id: String              = DEFAULT_STRING,
        override var createdAt: Long         = DEFAULT_LONG,
        override var lastUpdatedAt: Long     = DEFAULT_LONG,
        override var trackId: String         = DEFAULT_STRING,
        override var memberIds: List<String> = defaultList(),
        override var lastMessage: String     = DEFAULT_STRING
) : Chat
