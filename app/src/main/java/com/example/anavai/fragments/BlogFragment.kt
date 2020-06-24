package com.example.anavai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.adapters.BlogRecyclerAdapter
import com.example.anavai.adapters.ReviewRecyclerAdapter
import com.example.anavai.view_models.BlogViewModel
import com.example.anavai.view_models.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_blog.*
import kotlinx.android.synthetic.main.fragment_reviews.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlogFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        blog_recycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val blogViewModel: BlogViewModel by viewModel()
        GlobalScope.launch(Dispatchers.Main) {
            val blogs = blogViewModel.getBlogs().value!!
            blog_recycler.adapter = BlogRecyclerAdapter(blogs)
        }
    }

}