package bu.ac.kr.anyfeeling.service

import bu.ac.kr.anyfeeling.PlayerModel
import com.google.android.exoplayer2.Player

fun MusicEntity.mapper(id: Long): MusicModel =
    MusicModel(
        id = id,
        streamUrl = streamUrl,
        coverUrl = coverUrl,
        track = track,
        artist = artist
    )


fun MusicDto.mapper(): PlayerModel =
    PlayerModel(
        playMusicList = musics.mapIndexed{ index, musicEntity ->
            musicEntity.mapper(index.toLong())
        }
    )
fun SadMusicDto.mapper() : PlayerModel =
    PlayerModel(
        playMusicList = sadmusic.mapIndexed{ index, musicEntity ->
            musicEntity.mapper(index.toLong())
        }
    )


