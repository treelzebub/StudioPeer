package net.treelzebub.studiopeer.database

import com.google.firebase.database.DatabaseReference

/**
 * Created by Tre Murillo on 5/28/17
 */
interface Referenced {
    val path: String
    val reference: DatabaseReference
}