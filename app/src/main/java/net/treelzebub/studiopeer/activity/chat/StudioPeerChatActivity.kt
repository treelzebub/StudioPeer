package net.treelzebub.studiopeer.activity.chat

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.activity_chat.*
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.activity.StudioPeerActivity
import net.treelzebub.studiopeer.android.dismissKeyboard
import net.treelzebub.studiopeer.chat.StudioPeerChats
import net.treelzebub.studiopeer.database.DefaultValueEventListener

/**
 * Created by Tre Murillo on 5/28/17
 *
 * A Chat associated with a specific Track. Probably migrate to Fragment at somepoint.
 */
class StudioPeerChatActivity : StudioPeerActivity() {

    // TODO handle with Lifecycle components
    private val chatRef = StudioPeerChats.reference
    private val adapter by lazy {
        ChatAdapter { recycler.smoothScrollToPosition(it) }
    }
    private val trackName = "My Recording" // TODO intent extra

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
        input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                send.isClickable = !s.isNullOrBlank()
            }
        })
        send.setOnClickListener {
            dismissKeyboard()
            StudioPeerChats.send(trackName, input.text.toString())
            input.setText("")
        }
    }
}
