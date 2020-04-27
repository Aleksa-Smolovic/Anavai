package com.example.anavai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.adapters.CommentRecyclerAdapter
import com.example.anavai.models.Comment
import com.example.anavai.R
import com.example.anavai.view_models.CommentViewModel
import com.example.anavai.view_models.MediaInstanceViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_text_media_end_scene.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class TextMediaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_media, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_media_tab.addTab(text_media_tab.newTab().setText("Details"))
        text_media_tab.addTab(text_media_tab.newTab().setText("Reviews"))

        val commentList = ArrayList<Comment>()
        for (i in 0..10) {
            commentList.add(
                Comment(
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png"
                )
            )
        }

        val bottomSheetDialog = BottomSheetDialog(view.context)
        val bottomView = layoutInflater.inflate(R.layout.bottom_sheet_add_comment, null)
        bottomSheetDialog.setContentView(bottomView)

        add_comment_btn.setOnClickListener {
            bottomSheetDialog.show()
        }

        text_media_tab.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(context, "text", Toast.LENGTH_LONG).show()
                    if (tab.position == 0) {
                        text_holder.visibility = View.VISIBLE
                        comment_recycler.visibility = View.GONE
                    } else {
                        text_holder.visibility = View.GONE
                        comment_recycler.visibility = View.VISIBLE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        initRecycler()

    }

    private fun initRecycler() {
        val commentViewModel: CommentViewModel by viewModel()
        comment_recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        comment_recycler.adapter =
            CommentRecyclerAdapter(commentViewModel.getCommentList().value!!, requireContext())
    }

}
