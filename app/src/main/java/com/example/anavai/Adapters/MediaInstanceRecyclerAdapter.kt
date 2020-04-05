package com.example.anavai.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anavai.Fragments.SingleMediaFragment
import com.example.anavai.Models.MediaInstance
import com.example.anavai.R
import kotlinx.android.synthetic.main.recycler_item_media.view.*
import kotlinx.android.synthetic.main.recycler_item_media_instance.view.*


class MediaInstanceRecyclerAdapter(
    private val mediaInstanceList: ArrayList<MediaInstance>,
    private val context: Context
) : RecyclerView.Adapter<MediaInstanceRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_media_instance, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mediaInstanceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaInstance = mediaInstanceList[position]

        holder.title.text = mediaInstance.title
        holder.description.text = mediaInstance.description
        Glide.with(context)
            .load(mediaInstance.imageUrl)
            .into(holder.image)

        holder.container.setOnClickListener {
            it.findNavController().navigate(R.id.navigate_singleMedia_to_MusicMedia)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.media_instance_title) as TextView
        val image = itemView.findViewById(R.id.media_instance_image) as ImageView
        val description = itemView.findViewById(R.id.media_instance_description) as TextView
        val container: CardView = itemView.media_instance_container
    }

}