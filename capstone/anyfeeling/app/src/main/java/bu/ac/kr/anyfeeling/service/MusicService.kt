package bu.ac.kr.anyfeeling.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {
    @GET("/v3/e048fd4b-2021-4b4f-aa62-67c6be2bbc26")
    fun listMusics() : Call<MusicDto>
}