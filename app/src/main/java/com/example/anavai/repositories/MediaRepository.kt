package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.ApiService.ApiService
import com.example.anavai.models.Media
import com.example.anavai.R
import com.example.anavai.models.TestResponse

class MediaRepository(private val apiService: ApiService) {

    private var mediaList = ArrayList<Media>()

    suspend fun getTest():List<TestResponse>{
       return apiService.getTest().await()
    }

    fun getMediaList(): MutableLiveData<List<Media>>{
        populateMediaList()
        val data:MutableLiveData<List<Media>> = MutableLiveData()
        data.value = mediaList
        return data
    }

    private fun populateMediaList(){
        mediaList.add(
            Media(
                "Music",
                1,
                "http://4.bp.blogspot.com/_2UbsSBz9ckE/S5wy4wwOgJI/AAAAAAAAA8M/3kHzTarHkf0/s1600/beatsByTURNRed.png",
                R.color.overlay_music
            )
        )
        mediaList.add(
            Media(
                "Movies",
                2,
                "https://www.tokkoro.com/picsup/2860911-batman-batman-begins-the-dark-knight-the-dark-knight-rises-movies___movie-wallpapers.jpg",
                R.color.overlay_movies
            )
        )
        mediaList.add(
            Media(
                "Books",
                3,
                "https://www.wallpaperflare.com/static/512/909/111/book-old-vintage-chipped-wallpaper.jpg",
                R.color.overlay_books
            )
        )
        mediaList.add(
            Media(
                "Games",
                4,
                "https://wallpapercave.com/wp/wp1870469.jpg",
                R.color.overlay_games
            )
        )
        mediaList.add(
            Media(
                "Series",
                5,
                "http://2.bp.blogspot.com/-rYr479JwWpQ/TuCwjmzlp0I/AAAAAAAAEjM/tLo_gBSnTrE/s1600/sherlock2.jpg",
                R.color.overlay_series
            )
        )
        mediaList.add(
            Media(
                "Anime",
                6,
                "https://images4.alphacoders.com/102/thumb-1920-1028306.png",
                R.color.overlay_anime
            )
        )
    }

}