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

interface RomanticMusicService{
    @GET("/v3/35614017-dba8-48e4-94e5-5ee4a9b70a1f")

    fun listMusics3() : Call<MusicDto>
}
interface GloomyMusicService{
    @GET("/v3/90d211cd-e835-44e8-8b7b-a5237c601af0")
    fun listMusics4() : Call<MusicDto>
}
interface SexyMusicService{
    @GET("/v3/7f0740f5-2802-417e-a745-e697965a1724")
    fun listMusics5() : Call<MusicDto>
}
interface RelaxingMusicService{
    @GET("/v3/9cd64e9e-7521-4ab3-a879-e1637666cda0")
    fun listMusics6() : Call<MusicDto>
}
interface DarkMusicService{
    @GET("/v3/5884270a-9e56-4fde-8280-965ce0405075")
    fun listMusics6() : Call<MusicDto>
}
interface FunnyMusicService{
    @GET("/v3/fb6ca9e8-a4dd-4b83-905f-4c59ae65122a")
    fun listMusics7() : Call<MusicDto>
}