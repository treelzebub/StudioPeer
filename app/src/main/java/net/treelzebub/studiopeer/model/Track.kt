package net.treelzebub.studiopeer.model

interface Track {

    val id: Long

    val artist: String
    val title: String
    val time: String // The length of the song HH:MM:SS:mm
    val size: Long
    val lastUpdated: Long // TODO joda

    val position: Int
    val comments: List<Comment>
}