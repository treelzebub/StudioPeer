package net.treelzebub.studiopeer.model.impls

import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_INT
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_LONG
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.DEFAULT_STRING
import net.treelzebub.studiopeer.model.DatabaseObject.Companion.defaultMap
import net.treelzebub.studiopeer.model.entities.Track

/**
 * Created by Tre Murillo on 5/27/17
 */
class StudioPeerTrack(
    override var id: String                    = DEFAULT_STRING,
    override var createdAt: Long               = DEFAULT_LONG,
    override var lastUpdatedAt: Long           = DEFAULT_LONG,
    override var title: String                 = DEFAULT_STRING,
    override var artist: String                = DEFAULT_STRING,
    override var versions: Map<String, String> = defaultMap(),
    override var number: Int                   = DEFAULT_INT,
    override var length: Long                  = DEFAULT_LONG,
    override var size: Long                    = DEFAULT_LONG
) : Track
