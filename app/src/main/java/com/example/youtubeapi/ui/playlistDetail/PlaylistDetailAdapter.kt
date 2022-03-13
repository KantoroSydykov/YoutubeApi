package com.example.youtubeapi.ui.playlistDetail
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeapi.core.extensions.load
import com.example.youtubeapi.data.models.Items
import com.example.youtubeapi.databinding.ItemPlaylistRvBinding
class PlaylistDetailAdapter(private val list:List<Items>, private val clickListener:(id:String)->Unit): RecyclerView.Adapter<PlaylistDetailAdapter.ViewHolder>() {

    lateinit var binding: ItemPlaylistRvBinding

    inner  class ViewHolder(binding: ItemPlaylistRvBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun onBind(items: Items) {
            binding.imageEv.load(items.snippet.thumbnails.default.url)
            binding.playlistNameTv.text = items.snippet.title
            binding.playlistTv.visibility = View.GONE
            binding.playlistCountTv.text = items.snippet.publishedAt

            itemView.setOnClickListener {
                clickListener(items.id)
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

    override fun getItemCount(): Int = list.size
}