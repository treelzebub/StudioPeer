package net.treelzebub.studiopeer.activity.chat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_text_message_sent.view.*
import net.treelzebub.knapsack.extensions.inflate
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.android.users.StudioPeerUsers
import net.treelzebub.studiopeer.database.CollectionTypeIndicators
import net.treelzebub.studiopeer.model.impls.TextMessage
import net.treelzebub.studiopeer.view.picasso.CircleTransform

/**
 * Created by Tre Murillo on 5/28/17
 */
class ChatAdapter(private val scrollToPosition: (Int) -> Unit) : RecyclerView.Adapter<ChatAdapter.VH>() {

    init { setHasStableIds(true) }

    private var list = listOf<TextMessage>()

    fun set(snapshot: DataSnapshot) {
        val count = snapshot.childrenCount
        if (count == 0L) return
        val new = snapshot.getValue(CollectionTypeIndicators.getTextMessages)
        list = new.values
                .sortedBy { it.createdAt }
                .toMutableList()
        notifyDataSetChanged()
        scrollToPosition(list.lastIndex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layout = if (viewType == 0) R.layout.item_text_message_sent else R.layout.item_text_message_received
        return VH(parent.inflate(layout))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.set(list[position])
    }

    override fun getItemCount() = list.size

    private val myId = StudioPeerUsers.user!!.id
    override fun getItemViewType(position: Int): Int {
        val userId = list[position].userId
        return if (userId == myId) 0 else 1
    }

    inner class VH(private val v: View) : RecyclerView.ViewHolder(v) {
        fun set(message: TextMessage) {
            v.message.text = message.text
            Picasso.with(v.context)
                   .load(message.avatarUrl)
                   .transform(CircleTransform())
                   .into(v.avatar)
        }
    }
}