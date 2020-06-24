package com.example.anavai.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.anavai.R
import com.example.anavai.adapters.ActorRecyclerAdapter
import com.example.anavai.adapters.ReviewRecyclerAdapter
import com.example.anavai.view_models.ActorViewModel
import com.example.anavai.view_models.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_actors.*
import kotlinx.android.synthetic.main.fragment_reviews.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorsFragment : Fragment() {

    private val args: ActorsFragmentArgs by navArgs()
    private val actorViewModel: ActorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_actors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        actors_back_btn.setOnClickListener {
            activity!!.onBackPressed()
        }

        movie_actors_title.text = args.movieTitle
    }

    private fun initRecycler() {
        actors_recycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        GlobalScope.launch(Dispatchers.Main) {
            val actors = actorViewModel.getActors(args.movieId).value!!
            actors_recycler.adapter =
                ActorRecyclerAdapter(actors)
        }
    }

}