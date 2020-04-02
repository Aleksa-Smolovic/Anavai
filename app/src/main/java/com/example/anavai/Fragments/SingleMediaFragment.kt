package com.example.anavai.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.anavai.Adapters.MediaInstanceRecyclerAdapter
import com.example.anavai.Adapters.MediaViewpagerAdapter
import com.example.anavai.Models.Media
import com.example.anavai.Models.MediaInstance
import com.example.anavai.R
import com.google.android.material.appbar.CollapsingToolbarLayout


class SingleMediaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_single_media, container, false)
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

        val mediaInstanceList = ArrayList<MediaInstance>()
        for (i in 0..10){
            mediaInstanceList.add(
                MediaInstance(
                    "Rick and Morty",
                    1,
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png",
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png"
                )
            )
        }

        val mediaPager = rootView.findViewById(R.id.media_pager) as ViewPager2
        mediaPager.adapter = MediaViewpagerAdapter(mediaList, requireContext())

        val mediaInstanceRecycler =
            rootView.findViewById<RecyclerView>(R.id.media_instance_recycler)
        mediaInstanceRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mediaInstanceRecycler.adapter = MediaInstanceRecyclerAdapter(mediaInstanceList, requireContext())

        mediaInstanceRecycler.setBackgroundColor(resources.getColor(R.color.overlay_anime))

        val collapsingToolbarLayout =
            rootView.findViewById(R.id.collapsing_toolbar_layout) as CollapsingToolbarLayout

        mediaPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                collapsingToolbarLayout.title = mediaList[position].name
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        return rootView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Handler().postDelayed({
//            sharedElementEnterTransition = TransitionInflater
//                    .from(context).inflateTransition(
//                            android.R.transition.move
//                    )
//        },2500)

    }


}