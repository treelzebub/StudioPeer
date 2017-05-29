package net.treelzebub.studiopeer.activity.chat

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import kotlinx.android.synthetic.main.activity_chat.*
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.TAG
import net.treelzebub.studiopeer.activity.StudioPeerActivity
import net.treelzebub.studiopeer.chat.StudioPeerChats
import net.treelzebub.studiopeer.database.StudioPeerDb

/**
 * Created by Tre Murillo on 5/28/17
 *
 * A Chat associated with a specific Track. Probably migrate to Fragment at somepoint.
 */
class StudioPeerChatActivity : StudioPeerActivity() {

    // TODO handle with Lifecycle components
    private val chatRef = StudioPeerChats.reference
    private val adapter = ChatAdapter()
    private val trackName = "My Recording" // TODO intent extra

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        StudioPeerDb.listenForAdds(chatRef) {
            snapshot, _ ->
            adapter.set(snapshot)
            Log.d(TAG, "Received ${snapshot.childrenCount} chats.")
        }

        recycler.let {
            it.layoutManager = LinearLayoutManager(this).apply { reverseLayout = true }
            it.itemAnimator = DefaultItemAnimator()
            it.adapter = adapter
        }
        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                send.isClickable = !s.isNullOrEmpty()
            }
        })
        send.setOnClickListener {
            StudioPeerChats.send(trackName, input.text.toString())
            input.clearComposingText()
        }
    }
}
