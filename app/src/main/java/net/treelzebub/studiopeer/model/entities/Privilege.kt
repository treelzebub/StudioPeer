package net.treelzebub.studiopeer.model.entities

import java.util.*

/**
 * Created by Tre Murillo on 5/27/17
 *
 * TODO some day this will replace isAdmin checks on Members
 */
enum class Privilege {
    CanCrateProjects,
    CanDeleteProjects,
    CanInviteMembers,
    CanCreateTracks,
    CanDeleteTracks,
    CanUpdateTracks;

    companion object {
        val Admin  = EnumSet.allOf(Privilege::class.java)
        val Create = EnumSet.of(CanCrateProjects, CanCreateTracks)
        val Read   = EnumSet.noneOf(Privilege::class.java)
        val Update = EnumSet.of(CanInviteMembers, CanUpdateTracks)
        val Delete = EnumSet.of(CanDeleteProjects, CanDeleteTracks)
    }
}
