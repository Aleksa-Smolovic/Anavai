package com.example.anavai.fragments

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.adapters.MoviesRecyclerAdapter
import com.example.anavai.adapters.ReviewRecyclerAdapter
import com.example.anavai.models.Movie
import com.example.anavai.models.Review
import com.example.anavai.view_models.MovieViewModel
import com.example.anavai.view_models.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_reviews.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReviewsFragment : Fragment() {

    private val args: ReviewsFragmentArgs by navArgs()
    private val reviewViewModel: ReviewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        reviews_back_btn.setOnClickListener {
            activity!!.onBackPressed()
        }
        movie_review_title.text = args.movieTitle
    }

    private fun initRecycler() {
        reviews_recycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        GlobalScope.launch(Dispatchers.Main) {
            val reviews = reviewViewModel.getReviews(args.movieId).value!!
            if (reviews.isEmpty()) {
                no_reviews.visibility = View.VISIBLE
                reviews_recycler.visibility = View.GONE
            } else {
                no_reviews.visibility = View.GONE
                reviews_recycler.adapter =
                    ReviewRecyclerAdapter(reviews, requireContext())
            }
        }
    }

}