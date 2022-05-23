package bu.ac.kr.anyfeeling

import bu.ac.kr.anyfeeling.service.MusicModel


//음악이 다음 트랙으로 넘어갈 때
data class PlayerModel(
    private val playMusicList: List<MusicModel> = emptyList(),
    var currentPosition: Int = -1,
    var isWatchingPlayListView: Boolean = true,

) {

    fun getAdapterModels(): List<MusicModel> {
        return playMusicList.mapIndexed { index, musicModel ->
            val newItem = musicModel.copy(
                isPlaying = index == currentPosition
            )
            newItem
        }
    }

    fun updateCurrentPosition(musicModel: MusicModel) {
        currentPosition = playMusicList.indexOf(musicModel)
    }
    fun nextMusic(): MusicModel? {
        if (Companion.playMusicList.isEmpty()) return null

        Companion.currentPosition =
            if ((Companion.currentPosition + 1) == Companion.playMusicList.size) 0 else Companion.currentPosition + 1
        return Companion.playMusicList[Companion.currentPosition]
    }

    fun prevMusic(): MusicModel? {
        if (Companion.playMusicList.isEmpty()) return null

        Companion.currentPosition =
            if ((Companion.currentPosition - 1) < 0) Companion.playMusicList.lastIndex else Companion.currentPosition - 1

        return Companion.playMusicList[Companion.currentPosition]
    }

    fun currentMusicModel(): MusicModel? {
        if (Companion.playMusicList.isEmpty()) return null

        return Companion.playMusicList[Companion.currentPosition]
    }



companion object {
    private val playMusicList: List<MusicModel> = emptyList()
    var currentPosition: Int = -1
    var isWatchingPlayListView: Boolean = true

    fun nextMusic(): MusicModel? {
        if (playMusicList.isEmpty()) return null

        currentPosition =
            if ((currentPosition + 1) == playMusicList.size) 0 else currentPosition + 1
        return playMusicList[currentPosition]
    }

    fun prevMusic(): MusicModel? {
        if (playMusicList.isEmpty()) return null

        currentPosition =
            if ((currentPosition - 1) < 0) playMusicList.lastIndex else currentPosition - 1

        return playMusicList[currentPosition]
    }

    fun currentMusicModel(): MusicModel? {
        if (playMusicList.isEmpty()) return null

        return playMusicList[currentPosition]
    }
}
}
