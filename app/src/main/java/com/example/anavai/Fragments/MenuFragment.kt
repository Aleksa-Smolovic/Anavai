package com.example.anavai.Fragments

import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.Adapters.MediaRecyclerAdapter
import com.example.anavai.Models.Media
import com.example.anavai.R
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlin.math.hypot

class MenuFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu, container, false)



        val mediaList = ArrayList<Media>()
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

        val mediaRecycler = rootView.findViewById(R.id.media_recycler) as RecyclerView
        mediaRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mediaRecycler.adapter = MediaRecyclerAdapter(mediaList, requireContext())

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            removeCover()
        }, 500)
    }

    private fun removeCover() {
        val startRadius: Float =
            hypot((basic_cover.width).toDouble(), (basic_cover.height).toDouble()).toFloat()

        val cx: Float = (basic_cover.x + basic_cover.width / 2)
        val cy: Float = (basic_cover.y + basic_cover.height / 2)

        val expandAnimation: Animator = ViewAnimationUtils.createCircularReveal(
            basic_cover,
            (cx).toInt(),
            (cy).toInt(),
            startRadius,
            0f
        )

        expandAnimation.duration = 800
        expandAnimation.start()
        expandAnimation.doOnEnd {
            basic_cover.visibility = View.GONE
        }
    }

}