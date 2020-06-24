package com.example.anavai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.models.Actor
import com.example.anavai.models.Movie
import com.example.anavai.utils.loadImage


class ActorRecyclerAdapter(
    private val actors: List<Actor>
) : RecyclerView.Adapter<ActorRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actors[position]
        holder.image.loadImage(actor.image)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById(R.id.actor_image) as ImageView
    }

}