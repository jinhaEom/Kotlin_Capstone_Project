package bu.ac.kr.anyfeeling.first

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import bu.ac.kr.anyfeeling.PlayerModel
import bu.ac.kr.anyfeeling.R
import bu.ac.kr.anyfeeling.adapter.SadPlayListAdapter
import bu.ac.kr.anyfeeling.databinding.FragmentPlayerBinding
import bu.ac.kr.anyfeeling.service.MusicDto
import bu.ac.kr.anyfeeling.service.MusicModel
import bu.ac.kr.anyfeeling.service.MusicService.MusicService
import bu.ac.kr.anyfeeling.service.mapper
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SadActivity : AppCompatActivity(R.layout.fragment_player){

    private var model : PlayerModel = PlayerModel()
    private var player : SimpleExoPlayer?= null
    private lateinit var sadPlayListAdapter: SadPlayListAdapter


    private var binding : FragmentPlayerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding!!.root)

        val fragmentPlayerBinding = FragmentPlayerBinding.inflate(layoutInflater)
        binding = fragmentPlayerBinding
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
            val nextMusic = model.nextMusic() ?: return@setOnClickListener
            playMusic(nextMusic)
        }
        fragmentPlayerBinding.skipPrevImageView.setOnClickListener {
            val prevMusic = model.prevMusic() ?: return@setOnClickListener
            playMusic(prevMusic)
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
                    //플레이어가 재생이 될때 or 일시정지될때 callback 으로 내려오는 함수
                    super.onIsPlayingChanged(isPlaying)
                    if(isPlaying){
                        binding?.playControlImageView?.setImageResource(R.drawable.ic_baseline_pause_24)
                    }else{
                        binding?.playControlImageView?.setImageResource(R.drawable.ic_baseline_play_arrow_24)
                    }
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)

                    val newIndex = mediaItem?.mediaId ?: return
                    model.currentPosition = newIndex.toInt()
                    sadPlayListAdapter.submitList(model.getAdapterModels())
                }
            })

        }
    }

    private fun initRecyclerView(fragmentPlayerBinding: FragmentPlayerBinding) {
        sadPlayListAdapter = SadPlayListAdapter {
            playMusic(it)
        }
        fragmentPlayerBinding.playListRecyclerView.apply{
            adapter = sadPlayListAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


    private fun initPlayListButton(fragmentPlayerBinding: FragmentPlayerBinding) {
        fragmentPlayerBinding.playlistImageView.setOnClickListener {

            if(model.currentPosition == -1) return@setOnClickListener
            fragmentPlayerBinding.playerViewGroup.isVisible = model.isWatchingPlayListView
            fragmentPlayerBinding.playListViewGroup.isVisible = model.isWatchingPlayListView.not()

            model.isWatchingPlayListView = !model.isWatchingPlayListView
        }
        return
    }


    private fun getVideoListFromServer() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://run.mocky.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(MusicService::class.java)
            .also {
                it.listMusics()
                    .enqueue(object : Callback<MusicDto> {
                        override fun onResponse(
                            call: Call<MusicDto>,
                            response: Response<MusicDto>
                        ) {
                            response.body()?.let{

/*
                                model = MusicDto.mapper()
*/
                                setMusicList(model.getAdapterModels())
                                sadPlayListAdapter.submitList(model.getAdapterModels())
                            }
                        }

                        override fun onFailure(call: Call<MusicDto>, t: Throwable) {

                        }


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
        }
    }
    private fun playMusic(musicModel: MusicModel){
        model.updateCurrentPosition(musicModel)
        player?.seekTo(model.currentPosition,0)
        player?.play()
    }

}

