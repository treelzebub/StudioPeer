package net.treelzebub.studiopeer.activity.tracklist

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_tracklist.*
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.activity.StudioPeerActivity

class TracklistActivity : StudioPeerActivity() {

    private val adapter = TrackAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracklist)
        setupRecycler()
    }

    private fun setupRecycler() {
        recycler.let {
            it.layoutManager = LinearLayoutManager(this)
            it.itemAnimator = DefaultItemAnimator()
            it.adapter = adapter
        }
    }
}