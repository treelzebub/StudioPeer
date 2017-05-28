package net.treelzebub.studiopeer.model.entities

import net.treelzebub.studiopeer.model.DatabaseObject

/**
 * Created by Tre Murillo on 5/27/17
 */
interface Tracklist : DatabaseObject {

    // todo solve for representing different versions of the same Track

    var trackIds: List<String>
}