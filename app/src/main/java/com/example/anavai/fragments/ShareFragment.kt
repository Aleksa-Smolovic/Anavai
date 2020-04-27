package com.example.anavai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.adapters.MediaInstanceRecyclerAdapter
import com.example.anavai.api_service.ApiService
import com.example.anavai.R
import com.example.anavai.view_models.MediaInstanceViewModel
import com.example.anavai.view_models.MediaViewModel
import kotlinx.android.synthetic.main.fragment_share.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShareFragment : Fragment() {

    private lateinit var mediaInstanceRecycler: RecyclerView
    private val mediaInstanceViewModel: MediaInstanceViewModel by viewModel()
    private val mediaViewModel: MediaViewModel by viewModel()

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

        val apiService = ApiService()
//        val mediaViewModel = ViewModelProvider(this).get(MediaViewModel::class.java)

        search_btn.setOnClickListener {
                        GlobalScope.launch(Dispatchers.Main) {
//                            val response = apiService.getTest().await()
//                            println("Ovo je response: $response")
                            val response = mediaViewModel.getTest()
                            println("Ovo je response: $response")
            }
        }

        mediaInstanceRecycler =
            view.findViewById(R.id.search_media_recycler)
        initRecycler()
    }

    private fun initRecycler(){
        mediaInstanceRecycler.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mediaInstanceRecycler.adapter =
            MediaInstanceRecyclerAdapter(mediaInstanceViewModel.getMediaInstanceList().value!!, requireContext())
    }

}