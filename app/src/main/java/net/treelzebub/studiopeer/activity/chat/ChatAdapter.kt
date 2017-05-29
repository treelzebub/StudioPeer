package net.treelzebub.studiopeer.activity.chat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_text_message_sent.view.*
import net.treelzebub.knapsack.extensions.inflate
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.database.CollectionTypeIndicators
import net.treelzebub.studiopeer.model.impls.TextMessage

/**
 * Created by Tre Murillo on 5/28/17
 */

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.VH>() {

    private var list: List<TextMessage> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun set(snapshot: DataSnapshot) {
        list = snapshot.getValue(CollectionTypeIndicators.getTextMessages)
                .values
                .toList()
                .sortedByDescending { it.createdAt }
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): VH {
        return VH(parent.inflate(R.layout.item_text_message_sent))
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.set(list[position])

    override fun getItemCount() = list.size

    inner class VH(private val v: View) : RecyclerView.ViewHolder(v) {
        fun set(message: TextMessage) {
            v.message.text = message.text
            Picasso.with(v.context)
                   .load(message.avatarUrl)
                   .into(v.avatar)
        }
    }
}