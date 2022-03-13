package com.example.youtubeapi.ui.playlistDetail
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubeapi.core.extensions.gone
import com.example.youtubeapi.core.extensions.visible
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.uiBase.BaseActivity
import com.example.youtubeapi.core.uiBase.BaseViewModel
import com.example.youtubeapi.data.models.Playlist
import com.example.youtubeapi.databinding.ActivityPlaylistDetailBinding
import com.example.youtubeapi.ui.video.VideoActivity
import com.example.youtubeapi.utils.Constant


class PlaylistDetailActivity : BaseActivity<BaseViewModel,ActivityPlaylistDetailBinding>() {

    override val viewModel: PlaylistDetailViewModel by lazy{
        ViewModelProvider(this).get(PlaylistDetailViewModel::class.java)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this){
            binding.progressBar.isVisible =it
        }
    }

    override fun initView() {
        super.initView()
        intent.getStringExtra(Constant.KEY_PLAYLIST_ID)?.let { getData(it) }
    }

    override fun checkInternet() {
        super.checkInternet()
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(builder,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    runOnUiThread {
                        binding.titleTv.text =intent.getStringExtra(Constant.KEY_PLAYLIST_TITLE)
                        binding.descTv.text =intent.getStringExtra(Constant.KEY_PLAYLIST_DESC)
                        binding.checkInet.root.gone()
                        binding.playlistRv.visible()
                        binding.appBar.visible()
                    }
                }
                override fun onLost(network: Network) {
                    runOnUiThread {
                        binding.seriesTv.gone()
                        binding.appBar.gone()
                        binding.floatBtn.gone()
                        binding.checkInet.root.visible()
                        binding.playlistRv.gone()
                    }
                }
            }
        )

    }

    private fun getData(playlistId: String) {
        viewModel.getPlaylistItems(playlistId,null).observe(this){
            when(it.status){
                Status.SUCCESS->{
                    initRv(it.data)
                    val videoSeries = it.data?.pageInfo?.totalResults.toString() + " video series"
                    binding.seriesTv.text = videoSeries
                    viewModel.loading.postValue(false)
                }
                Status.ERROR-> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)

                }
                Status.LOADING->{
                    viewModel.loading.postValue(true)

                }
            }
        }
    }

    private fun initRv(data: Playlist?) {
        binding.playlistRv.layoutManager = LinearLayoutManager(this)
        binding.playlistRv.adapter =
            data?.items?.let {
                PlaylistDetailAdapter(it,this::clickListener) }
    }

    private  fun clickListener(id: String){
        Intent(this, VideoActivity::class.java).apply {
            putExtra(Constant.KEY_PLAYLIST_ID,id)
            startActivity(this)
        }
    }

    override fun initListener() {
        super.initListener()
        binding.checkInet.tryAgain.setOnClickListener {
            checkInternet()
        }
        binding.backTv.setOnClickListener {
            finish()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPlaylistDetailBinding {
        return ActivityPlaylistDetailBinding.inflate(inflater)
    }

}