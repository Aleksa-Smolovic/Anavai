package com.example.anavai.fragments

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
import com.example.anavai.adapters.CategoryRecyclerAdapter
import com.example.anavai.R
import com.example.anavai.view_models.CategoryViewModel
import com.example.anavai.view_models.MediaViewModel
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.hypot

class MenuFragment : Fragment() {

    lateinit var categoryRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_menu, container, false)
        categoryRecyclerView = rootView.findViewById(R.id.category_recycler)
        recyclerInit()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            removeCover()
        }, 500)
    }

    private fun recyclerInit() {
        val categoryViewModel: CategoryViewModel by viewModel()
        categoryRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        GlobalScope.launch(Dispatchers.Main) {
            categoryRecyclerView.adapter =
                CategoryRecyclerAdapter(categoryViewModel.getCategories().value!!, requireContext())
        }
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