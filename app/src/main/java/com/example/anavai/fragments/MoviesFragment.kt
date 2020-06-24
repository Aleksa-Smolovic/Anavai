package com.example.anavai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.anavai.adapters.MoviesRecyclerAdapter
import com.example.anavai.adapters.CategoryViewpagerAdapter
import com.example.anavai.R
import com.example.anavai.models.Category
import com.example.anavai.models.Movie
import com.example.anavai.view_models.CategoryViewModel
import com.example.anavai.view_models.MovieViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private lateinit var categoryViewpager: ViewPager2
    private lateinit var moviesRecycler: RecyclerView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private val args: MoviesFragmentArgs by navArgs()
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movies: List<Movie>
    private lateinit var categories: List<Category>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies, container, false)

        collapsingToolbarLayout =
            rootView.findViewById(R.id.collapsing_toolbar_layout) as CollapsingToolbarLayout
        categoryViewpager = rootView.findViewById(R.id.category_pager)
        moviesRecycler = rootView.findViewById(R.id.movies_recycler)

        initViewpager()
        initRecycler(args.categoryId)

        return rootView
    }

    private fun initViewpager() {
        val categoryViewModel: CategoryViewModel by viewModel()
        GlobalScope.launch(Dispatchers.Main) {
            categories = categoryViewModel.getCategories().value!!
            categoryViewpager.adapter = CategoryViewpagerAdapter(categories, requireContext())
            categoryViewpager.currentItem = args.itemPosition
        }
        pagerListener()
    }

    private fun updateMovies(
        categoryId: Long
    ) {
        GlobalScope.launch(Dispatchers.Main) {
            movies = movieViewModel.getMovieList(categoryId).value!!
            moviesRecycler.adapter = MoviesRecyclerAdapter(movies)
            moviesRecycler.invalidate();

//            adapter!!.notifyDataSetChanged()
        }
    }

    private fun initRecycler(categoryId: Long) {
        moviesRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        GlobalScope.launch(Dispatchers.Main) {
            movies = movieViewModel.getMovieList(categoryId).value!!
            moviesRecycler.adapter = MoviesRecyclerAdapter(movies)
        }
        moviesRecycler.setBackgroundColor(resources.getColor(R.color.overlay_anime))
    }

    private fun pagerListener() {
        categoryViewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                collapsingToolbarLayout.title = categories[position].name
                if (moviesRecycler.adapter != null)
                    updateMovies(categories[position].id)
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }

}