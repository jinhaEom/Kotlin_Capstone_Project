package bu.ac.kr.anyfeeling.first

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.anyfeeling.PlayListAdapter
import bu.ac.kr.anyfeeling.PlayerModel
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.databinding.FragmentPlayerBinding
import bu.ac.kr.anyfeeling.service.MusicDto
import bu.ac.kr.anyfeeling.service.MusicModel
import bu.ac.kr.anyfeeling.service.MusicService
import bu.ac.kr.anyfeeling.service.mapper
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HappyActivity: AppCompatActivity(R.layout.fragment_player) {

    private var model : PlayerModel = PlayerModel()
    private var binding : FragmentPlayerBinding? = null
    private var isWatchingPlayListView = true
    private var player : SimpleExoPlayer?= null
    private lateinit var playListAdapter: PlayListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val fragmentPlayerBinding = FragmentPlayerBinding.inflate(layoutInflater)
        binding = fragmentPlayerBinding
        setContentView(binding!!.root)
        initPlayView(fragmentPlayerBinding)
        initPlayListButton(fragmentPlayerBinding)
        initPlayControlButtons(fragmentPlayerBinding)
        initRecyclerView(fragmentPlayerBinding)
        getVideoListFromServer()

    }

    private fun initPlayControlButtons(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playControlImageView.setOnClickListener {
            val player = this.player?: return@setOnClickListener

            if(player.isPlaying){
                player.pause()
            }else{
                player.play()
            }
        }
        fragmentPlayerBinding.skipNextImageView.setOnClickListener {

        }
        fragmentPlayerBinding.skipPrevImageView.setOnClickListener {

        }
    }

    private fun initPlayView(fragmentPlayerBinding: FragmentPlayerBinding) {
        this.let{
            player = SimpleExoPlayer.Builder(this).build()
        }

        fragmentPlayerBinding.playerView.player = player

        binding.let { binding ->

            player?.addListener(object: Player.EventListener{

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    //플레이어가 재생이 될때 or 일시정지될때 callback으로 내려오는 함수
                    super.onIsPlayingChanged(isPlaying)
                    if(isPlaying){
                        binding?.playControlImageView?.setImageResource(R.drawable.ic_baseline_pause_24)
                    }else{
                        binding?.playControlImageView?.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    }
                }
            })

        }
    }

    private fun initRecyclerView(fragmentPlayerBinding: FragmentPlayerBinding) {
        playListAdapter = PlayListAdapter {
            // todo 음악 재생
        }
        fragmentPlayerBinding.playListRecyclerView.apply{
            adapter = playListAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


    private fun initPlayListButton(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playlistImageView.setOnClickListener {

            //todo 만약 서버에서 데이터가 다 불려오지 않은 상태일때
            fragmentPlayerBinding.playerViewGroup.isVisible = isWatchingPlayListView
            fragmentPlayerBinding.playListViewGroup.isVisible = isWatchingPlayListView.not()

            isWatchingPlayListView = !isWatchingPlayListView
        }
        return
    }


    private fun getVideoListFromServer(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MusicService::class.java)
            .also {
                it.listMusics()
                    .enqueue(object: Callback<MusicDto>{
                        override fun onResponse(call: Call<MusicDto>, response: Response<MusicDto>) {
                            response.body()?.let{
                                val modelList = it.musics.mapIndexed{ index, musicEntity ->
                                    musicEntity.mapper(index.toLong())
                                }
                                setMusicList(modelList)
                                playListAdapter.submitList(modelList)
                            }
                        }

                        override fun onFailure(call: Call<MusicDto>, t: Throwable) {}

                    })
            }
    }
    private fun setMusicList(modelList: List<MusicModel>){
        this.let{
            player?.addMediaItems(modelList.map { musicModel ->
                MediaItem.Builder()
                    .setMediaId(musicModel.id.toString())
                    .setUri(musicModel.streamUrl)
                    .build()
            })
            player?.prepare()
            player?.play()
        }
    }

}