package com.example.youtubeapi.repositories
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youtubeapi.utils.Constant
import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.BuildConfig.API_KEY
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.data.remote.ApiService
import com.example.youtubeapi.core.network.RetrofitClient
import com.example.youtubeapi.core.network.result.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private val apiService: ApiService by lazy{
        RetrofitClient.create()
    }


    fun getPlaylistsItem(playlistId: String, pageToken: String?):LiveData<Resource<Playlist>>{

        val data = MutableLiveData<Resource<Playlist>>()
        data.value = Resource.loading()
        apiService.getPlaylistItems(Constant.part,playlistId,API_KEY,Constant.maxResults,pageToken)
            .enqueue(object : Callback<Playlist> {

                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful){
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {
                    data.value  = Resource.error(t.localizedMessage)
                }
            })
        return  data
    }

    fun getPlaylists(): LiveData<Resource<Playlist>> {

        val data= MutableLiveData<Resource<Playlist>>()
        data.value = Resource.loading()


        apiService.getPlaylists(Constant.part, Constant.channelId, BuildConfig.API_KEY, Constant.maxResults)
            .enqueue(object: Callback<Playlist> {

                override fun onResponse(call: Call<Playlist>, response: Response<Playlist>) {
                    if (response.isSuccessful){
                        data.value = Resource.success(response.body())
                    }
                }

                override fun onFailure(call: Call<Playlist>, t: Throwable) {

                    data.value = Resource.error(t.localizedMessage)
                }

            })
        return data
    }

}