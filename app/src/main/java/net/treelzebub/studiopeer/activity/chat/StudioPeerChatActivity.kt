package net.treelzebub.studiopeer.activity.chat

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_chat.*
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.activity.StudioPeerActivity
import net.treelzebub.studiopeer.chat.StudioPeerChats
import net.treelzebub.studiopeer.database.DefaultValueEventListener
import net.treelzebub.studiopeer.view.onTextChanged

/**
 * Created by Tre Murillo on 5/28/17
 *
 * A Chat associated with a specific Track. Probably migrate to Fragment at some point.
 */
class StudioPeerChatActivity : StudioPeerActivity() {

    // TODO handle db reference with Lifecycle components
    private val trackName = "Debug" // TODO intent extra
    private val chatRef   = StudioPeerChats.reference.child(trackName)

    private val adapter by lazy {
        ChatAdapter { recycler.smoothScrollToPosition(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        listenForChats()
        setupUi()
    }

    private fun listenForChats() {
        chatRef.addValueEventListener(DefaultValueEventListener {
            snapshot -> adapter.set(snapshot)
        })
    }

    private fun setupUi() {
        recycler.let {
            it.layoutManager = LinearLayoutManager(this).apply { stackFromEnd = true }
            it.itemAnimator = SlideInUpAnimator()
            it.adapter = adapter
        }
        input.onTextChanged {
            s, _, _, _ ->
            send.isClickable = !s.isNullOrBlank()
        }
        send.setOnClickListener {
            StudioPeerChats.send(trackName, input.text.toString())
            input.setText("")
        }
    }
}
