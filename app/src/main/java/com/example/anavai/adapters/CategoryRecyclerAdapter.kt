package com.example.anavai.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.models.Category
import com.example.anavai.models.overlays
import com.example.anavai.utils.loadImage
import kotlinx.android.synthetic.main.recycler_item_media.view.*
import kotlin.random.Random


class CategoryRecyclerAdapter(
    private val categories: List<Category>,
    private val context: Context
) : RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_media, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.name.text = category.name
        holder.image.loadImage(category.image)

        holder.overlay.setBackgroundColor(ContextCompat.getColor(context, overlays[Random.nextInt(0, 5)]))
        ViewCompat.setTransitionName(holder.image, "Test_$position")

        holder.container.setOnClickListener {
            val bundle = Bundle().apply {
                putInt("position", position)
            }
            val extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(holder.image, ViewCompat.getTransitionName(holder.image)!!)
                .build()
            it.findNavController().navigate(R.id.navigate_Menu_to_SingleMedia)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.media_name) as TextView
        val image = itemView.findViewById(R.id.media_image) as ImageView
        val overlay = itemView.findViewById(R.id.media_overlay) as LinearLayout
        val container: CardView = itemView.media_item_container
    }

}