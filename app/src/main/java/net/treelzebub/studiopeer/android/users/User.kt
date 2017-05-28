package net.treelzebub.studiopeer.android.users

import net.treelzebub.studiopeer.model.DatabaseObject

/**
 * Created by Tre Murillo on 5/28/17
 */

interface User : DatabaseObject {
    var name: String
    var email: String
}

class NoUserException(msg: String) : IllegalStateException(msg)