package com.example.youtubeapi.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.R
import com.example.youtubeapi.data.models.Items
import com.example.youtubeapi.databinding.ItemPlaylistRvBinding
import com.example.youtubeapi.core.extensions.load
import kotlin.reflect.KFunction3

class PlaylistAdapter(private val list:List<Items>, private val clickListener: KFunction3<String, String, String, Unit>): RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    lateinit var binding:ItemPlaylistRvBinding

    inner class ViewHolder(binding: ItemPlaylistRvBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun onBind(items: Items) {
            binding.imageEv.load(items.snippet.thumbnails.default.url)
            binding.playlistNameTv.text = items.snippet.title
            binding.playlistCountTv.text = String.format(itemView.context.getString(R.string.videoSeries),items.contentDetails.itemCount.toString())
            itemView.setOnClickListener{
                clickListener(items.id,items.snippet.title,items.snippet.description)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPlaylistRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int  = list.size


}