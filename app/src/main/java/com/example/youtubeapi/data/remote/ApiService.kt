package com.example.youtubeapi.data.remote
import com.example.youtubeapi.data.models.Playlist
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part")part:String,
        @Query("channelId")channelId:String,
        @Query("key")apiKey:String,
        @Query("maxResults")maxResult:Int
    ):Call<Playlist>


    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("part") part: String,
        @Query("playlistId") channelId: String,
        @Query("key") key: String,
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String?
    ): Call<Playlist>
}