package com.example.anavai.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.models.Category
import com.example.anavai.models.overlays
import com.example.anavai.utils.loadImage
import kotlin.random.Random

class CategoryViewpagerAdapter(private val categories: List<Category>, private val context: Context): RecyclerView.Adapter<CategoryViewpagerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item_media, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val media = categories[position]

        holder.image.loadImage(media.image)
        holder.overlay.setBackgroundColor(ContextCompat.getColor(context, overlays[Random.nextInt(0, 5)]))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById(R.id.pager_media_image) as ImageView
        val overlay = itemView.findViewById(R.id.pager_media_overlay) as LinearLayout
    }
}