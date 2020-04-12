package com.example.anavai.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.Adapters.CommentRecyclerAdapter
import com.example.anavai.Models.Comment
import com.example.anavai.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.fragment_text_media.*


/**
 * A simple [Fragment] subclass.
 */
class TextMediaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

        val commentRecycler = view.findViewById(R.id.comment_recycler) as RecyclerView
        val textMediaTabLayout = view.findViewById(R.id.text_media_tab) as TabLayout
        val textHolder = view.findViewById(R.id.text_holder) as ScrollView

        commentRecycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        commentRecycler.adapter = CommentRecyclerAdapter(commentList, requireContext())

        textMediaTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selectedPosition: Int = tab.position
                if (selectedPosition == 0) {
                    textHolder.visibility = View.VISIBLE
                    commentRecycler.visibility = View.GONE
                } else {
                    textHolder.visibility = View.GONE
                    commentRecycler.visibility = View.VISIBLE
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

    }


}
