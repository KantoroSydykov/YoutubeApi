package com.example.youtubeapi.ui.playlistDetail

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist

class PlaylistDetailViewModel: BaseViewModel() {

    fun getPlaylistItems(playlistId: String, pageToken: String?): LiveData<Resource<Playlist>> {
        return App().repository.getPlaylistsItem(playlistId,pageToken)
    }

}