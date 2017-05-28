package net.treelzebub.studiopeer.model.impls

import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_BOOLEAN
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_LONG
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_STRING
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.defaultList
import net.treelzebub.studiopeer.model.entities.Member

/**
 * Created by Tre Murillo on 5/27/17
 */
data class StudioPeerMember(
        override var id: String               = DEFAULT_STRING,
        override var createdAt: Long          = DEFAULT_LONG,
        override var lastUpdatedAt: Long      = DEFAULT_LONG,
        override var isAdmin: Boolean         = DEFAULT_BOOLEAN,
        override var projectIds: List<String> = defaultList(),
        override var name: String             = DEFAULT_STRING,
        override var email: String            = DEFAULT_STRING,
        override var avatarUrl: String        = DEFAULT_STRING
) : Member
