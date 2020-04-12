package com.example.anavai.Fragments

import android.app.Notification
import android.app.NotificationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.anavai.Adapters.MediaInstanceRecyclerAdapter
import com.example.anavai.Adapters.MediaViewpagerAdapter
import com.example.anavai.Models.Media
import com.example.anavai.Models.MediaInstance
import com.example.anavai.R
import kotlinx.android.synthetic.main.fragment_share.*

class ShareFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_share, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val remoteView: RemoteViews = RemoteViews(activity?.packageName, R.layout.test)

        val notificationManager: NotificationManagerCompat =
            NotificationManagerCompat.from(view.context)

//        media_player_btn.setOnClickListener {
//            val notification:Notification = NotificationCompat.Builder(view.context, "CHANNEL_ID")
//                .setSmallIcon(R.drawable.ic_play_arrow_black_24dp)
//                .setCustomBigContentView(remoteView)
//                .setCustomContentView(remoteView)
//                .build()
//
//            notificationManager.notify(1, notification)
//        }

        val mediaInstanceList = ArrayList<MediaInstance>()
        for (i in 0..10) {
            mediaInstanceList.add(
                MediaInstance(
                    "Rick and Morty",
                    1,
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png",
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png"
                )
            )
        }

        val mediaInstanceRecycler =
            view.findViewById<RecyclerView>(R.id.search_media_recycler)
        mediaInstanceRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mediaInstanceRecycler.adapter =
            MediaInstanceRecyclerAdapter(mediaInstanceList, requireContext())

    }

}