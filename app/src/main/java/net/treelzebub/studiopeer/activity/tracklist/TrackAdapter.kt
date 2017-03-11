package net.treelzebub.studiopeer.activity.tracklist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.treelzebub.studiopeer.R
import net.treelzebub.studiopeer.bindView
import net.treelzebub.studiopeer.model.Track

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackHolder>() {

    init { setHasStableIds(true) }

    var tracks = listOf<Track>()
        set(value) {
            field = value.sortedByDescending { it.lastUpdated }
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackHolder(v)
    }

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        val track = tracks[position]
        holder.apply {
            artist.text = track.artist
            title.text  = track.title
            time.text   = track.time
        }
    }

    override fun getItemCount() = tracks.size

    override fun getItemViewType(position: Int) = 0

    override fun getItemId(position: Int) = tracks[position].id

    class TrackHolder(v: View) : RecyclerView.ViewHolder(v) {
        val artist by bindView<TextView>(R.id.artist)
        val title  by bindView<TextView>(R.id.title)
        val time   by bindView<TextView>(R.id.time)
    }
}