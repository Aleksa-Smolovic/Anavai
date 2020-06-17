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
import com.example.anavai.view_models.CategoryViewModel
import com.example.anavai.view_models.MovieViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment() {

    private lateinit var categoryPager: ViewPager2
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var moviesRecycler: RecyclerView
    private val args: MoviesFragmentArgs by navArgs()
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_single_media, container, false)

        categoryPager = rootView.findViewById(R.id.media_pager) as ViewPager2
        moviesRecycler =
            rootView.findViewById(R.id.movies_recycler)
        collapsingToolbarLayout =
            rootView.findViewById(R.id.collapsing_toolbar_layout) as CollapsingToolbarLayout

        initViewpager()
        initRecycler()
        return rootView
    }

    private fun initViewpager() {
        val categoryViewModel: CategoryViewModel by viewModel()
        var categories: List<Category>
        GlobalScope.launch(Dispatchers.Main) {
            categories = categoryViewModel.getCategories().value!!
            categoryPager.adapter = CategoryViewpagerAdapter(categories, requireContext())
            categoryPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    collapsingToolbarLayout.title = categories[position].name
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
            categoryPager.currentItem = args.categoryPosition
        }

    }

    private fun initRecycler() {
        moviesRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        GlobalScope.launch(Dispatchers.Main) {
            moviesRecycler.adapter =
                MoviesRecyclerAdapter(
                    movieViewModel.getMovieList().value!!
                )
        }
        moviesRecycler.setBackgroundColor(resources.getColor(R.color.overlay_anime))
    }

}