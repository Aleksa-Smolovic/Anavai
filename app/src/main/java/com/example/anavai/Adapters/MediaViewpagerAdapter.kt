package com.example.anavai.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anavai.Models.Media
import com.example.anavai.R

class MediaViewpagerAdapter(private val mediaList: ArrayList<Media>, private val context: Context): RecyclerView.Adapter<MediaViewpagerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item_media, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val media = mediaList[position]

        Glide.with(context)
            .load(media.imageUrl)
            .into(holder.image)

        holder.overlay.setBackgroundColor(ContextCompat.getColor(context, media.overlayColor))

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById(R.id.pager_media_image) as ImageView
        val overlay = itemView.findViewById(R.id.pager_media_overlay) as LinearLayout
    }
}