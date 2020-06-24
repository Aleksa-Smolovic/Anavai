package com.example.anavai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.fragments.MenuFragmentDirections
import com.example.anavai.models.Blog
import com.example.anavai.models.Category
import com.example.anavai.models.overlays
import com.example.anavai.utils.loadImage
import kotlinx.android.synthetic.main.recycler_item_media.view.*
import kotlin.random.Random


class BlogRecyclerAdapter(
    private val blogs: List<Blog>
) : RecyclerView.Adapter<BlogRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_blog, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return blogs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blog = blogs[position]
        holder.title.text = blog.title
        holder.image.loadImage(blog.image)
        holder.text.text = blog.text
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.blog_title) as TextView
        val image = itemView.findViewById(R.id.blog_image) as ImageView
        val text = itemView.findViewById(R.id.blog_text) as TextView
    }

}