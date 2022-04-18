package bu.ac.kr.anyfeeling.first

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
import bu.ac.kr.anyfeeling.service.MusicService
import bu.ac.kr.anyfeeling.service.mapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HappyActivity: AppCompatActivity(R.layout.fragment_player) {

    private var model : PlayerModel = PlayerModel()
    private var binding : FragmentPlayerBinding? = null
    private var isWatchingPlayListView = true
    private lateinit var playListAdapter: PlayListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val fragmentPlayerBinding = FragmentPlayerBinding.inflate(layoutInflater)
        binding = fragmentPlayerBinding
        setContentView(binding!!.root)
        initPlayListButton(fragmentPlayerBinding)
        initRecyclerView(fragmentPlayerBinding)
        getVideoListFromServer()

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
                                playListAdapter.submitList(modelList)
                            }
                        }

                        override fun onFailure(call: Call<MusicDto>, t: Throwable) {}

                    })
            }
    }

}