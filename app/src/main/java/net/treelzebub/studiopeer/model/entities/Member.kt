package net.treelzebub.studiopeer.model.entities

import net.treelzebub.studiopeer.model.DatabaseObject

/**
 * Created by Tre Murillo on 5/23/17
 */
interface Member : DatabaseObject {

//    TODO some day this will replace isAdmin
//    var privileges: Set<Privilege>

    /**
     * Is this [Member] a Project Administrator? Administrators may modify this field.
     */
    var isAdmin: Boolean

    /**
     * [Project]s associated with this [Member]. Administrators may modify this field.
     */
    var projectIds: List<String>

    /**
     * [Member]'s name, for display. Read only.
     */
    var name: String

    /**
     * [Member]'s email address. Read only.
     */
    var email: String

    /**
     * [Member]'s avatar URL. Read only.
     */
    var avatarUrl: String
}