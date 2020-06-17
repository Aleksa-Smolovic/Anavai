package com.example.anavai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.models.Movie
import com.example.anavai.utils.loadImage
import kotlinx.android.synthetic.main.recycler_item_movie.view.*


class MoviesRecyclerAdapter(
    private val mediaInstanceList: List<Movie>
) : RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mediaInstanceList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaInstance = mediaInstanceList[position]

        holder.title.text = mediaInstance.title
        holder.description.text = mediaInstance.text
        holder.image.loadImage(mediaInstance.image)

        holder.container.setOnClickListener {
//            it.findNavController().navigate(R.id.navigate_singleMedia_to_MusicMedia)
            it.findNavController().navigate(R.id.navigate_SingleMedia_to_TextMedia)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.media_instance_title) as TextView
        val image = itemView.findViewById(R.id.media_instance_image) as ImageView
        val description = itemView.findViewById(R.id.media_instance_description) as TextView
        val container: CardView = itemView.media_instance_container
    }

}