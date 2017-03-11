package net.treelzebub.studiopeer.model

interface Comment {

    val author: String
    val date: Long // TODO joda
    val content: CharSequence
}