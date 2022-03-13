package com.example.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist

class MyPlaylistViewModel: BaseViewModel() {


    fun getPlaylists(): LiveData<Resource<Playlist>> {
        return App().repository.getPlaylists()
    }

}
