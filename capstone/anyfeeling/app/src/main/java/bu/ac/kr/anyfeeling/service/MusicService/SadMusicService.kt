package bu.ac.kr.anyfeeling.service.MusicService


import bu.ac.kr.anyfeeling.service.MusicDto
import retrofit2.Call
import retrofit2.http.GET

interface SadMusicService {
    @GET("/v3/5230bab4-070a-4317-85cd-07eed12bbc6d")
    fun listMusics() : Call<MusicDto>
}