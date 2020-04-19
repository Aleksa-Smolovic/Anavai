package com.example.anavai.Adapters

import android.content.Context
import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anavai.Models.Media
import com.example.anavai.R
import kotlinx.android.synthetic.main.recycler_item_media.view.*


class MediaRecyclerAdapter(private val mediaList: List<Media>, private val context: Context) : RecyclerView.Adapter<MediaRecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_media, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val media = mediaList[position]

        holder.name.text = media.name
        Glide.with(context)
            .load(media.imageUrl)
            .into(holder.image)

        holder.overlay.setBackgroundColor(ContextCompat.getColor(context, media.overlayColor))
        ViewCompat.setTransitionName(holder.image, "Test_$position")

        holder.container.setOnClickListener{
            val bundle = Bundle().apply {
                putInt("position", position)
            }
            val extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(holder.image, ViewCompat.getTransitionName(holder.image)!!)
                .build()
            it.findNavController().navigate(R.id.navigate_Menu_to_SingleMedia)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById(R.id.media_name) as TextView
        val image = itemView.findViewById(R.id.media_image) as ImageView
        val overlay = itemView.findViewById(R.id.media_overlay) as LinearLayout
        val container: CardView = itemView.media_item_container
    }

}