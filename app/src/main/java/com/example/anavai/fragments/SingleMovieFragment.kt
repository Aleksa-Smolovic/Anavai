package com.example.anavai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.anavai.R
import com.example.anavai.models.Movie
import com.example.anavai.utils.loadImage
import com.example.anavai.view_models.MovieViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.bottom_sheet_add_comment.*
import kotlinx.android.synthetic.main.fragment_text_media.*
import kotlinx.android.synthetic.main.fragment_text_media.details_image
import kotlinx.android.synthetic.main.fragment_text_media.release_date
import kotlinx.android.synthetic.main.fragment_text_media.title_start
import kotlinx.android.synthetic.main.fragment_text_media_end_scene.*
import kotlinx.android.synthetic.main.fragment_text_media_end_scene.add_comment_btn
import kotlinx.android.synthetic.main.fragment_text_media_end_scene.details_text
import kotlinx.android.synthetic.main.fragment_text_media_end_scene.text_media_tab
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class SingleMovieFragment : Fragment() {

    private val args: SingleMovieFragmentArgs by navArgs()
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_media_tab.addTab(text_media_tab.newTab().setText("Details"))
        text_media_tab.addTab(text_media_tab.newTab().setText("Actors"))
        text_media_tab.addTab(text_media_tab.newTab().setText("Reviews"))

        val bottomSheetDialog = BottomSheetDialog(view.context)
        val bottomView = layoutInflater.inflate(R.layout.bottom_sheet_add_comment, null)
        bottomSheetDialog.setContentView(bottomView)

        add_comment_btn.setOnClickListener {
            bottomSheetDialog.show()
        }

        text_media_tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val action = when (tab.position) {
                    1 -> {
                        SingleMovieFragmentDirections.singleMovieFragmentToActorsFragment(args.movieId, title_start.text.toString())
                    }
                    2 -> {
                        SingleMovieFragmentDirections.singleMovieFragmentToReviewsFragment(args.movieId, title_start.text.toString())
                    }
                    else -> {
                        return;
                    }
                }
                findNavController().navigate(action)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.Main) {
            val movie: Movie = movieViewModel.getMovie(args.movieId)
            title_start.text = movie.title
            rating.rating = movie.rating!!
            details_text.text = movie.text
            details_image.loadImage(movie.image)
            release_date.text = movie.releaseDate
        }
    }

}
