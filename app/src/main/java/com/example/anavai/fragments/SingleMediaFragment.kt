package com.example.anavai.fragments

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
import com.example.anavai.models.Media
import com.example.anavai.R
import com.example.anavai.viewModels.MediaInstanceViewModel
import com.example.anavai.viewModels.MediaViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.viewModel


class SingleMediaFragment : Fragment() {

    private lateinit var mediaPager: ViewPager2
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var mediaInstanceRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_single_media, container, false)

        mediaPager = rootView.findViewById(R.id.media_pager) as ViewPager2
        mediaInstanceRecycler =
            rootView.findViewById(R.id.media_instance_recycler)
        collapsingToolbarLayout =
            rootView.findViewById(R.id.collapsing_toolbar_layout) as CollapsingToolbarLayout

        initViewpager()
        initRecycler()

        return rootView
    }


    private fun initViewpager() {
        val mediaViewModel : MediaViewModel by viewModel()
        val mediaList:List<Media> = mediaViewModel.getMediaList().value!!
        mediaPager.adapter = MediaViewpagerAdapter(mediaList, requireContext())
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
    }

    private fun initRecycler(){
        val mediaInstanceViewModel: MediaInstanceViewModel by viewModel()
        mediaInstanceRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mediaInstanceRecycler.adapter =
            MediaInstanceRecyclerAdapter(mediaInstanceViewModel.getMediaInstanceList().value!!, requireContext())
        mediaInstanceRecycler.setBackgroundColor(resources.getColor(R.color.overlay_anime))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val position = arguments?.getInt("position")
//        ViewCompat.setTransitionName(media_image, "Test_$position")
//        Toast.makeText(context, "OVO: "+ media_image.transitionName, Toast.LENGTH_LONG).show()
    }

}