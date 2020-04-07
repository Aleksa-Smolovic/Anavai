package com.example.anavai.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.anavai.R
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

        text_media_tab.addTab(text_media_tab.newTab().setText("Recent"))
        text_media_tab.addTab(text_media_tab.newTab().setText("Popular"))
    }

}
