package bu.ac.kr.anyfeeling.service.MusicService

import bu.ac.kr.anyfeeling.service.MusicDto
import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("/v3/e048fd4b-2021-4b4f-aa62-67c6be2bbc26")

    fun listMusics() : Call<MusicDto>

}
interface SadMusicService{
    @GET("/v3/5230bab4-070a-4317-85cd-07eed12bbc6d")

    fun listMusics2() : Call<MusicDto>
}
