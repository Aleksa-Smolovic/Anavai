package com.example.anavai.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.anavai.models.Review
import com.example.anavai.R


class ReviewRecyclerAdapter(private val reviewList: List<Review>, private val context: Context) : RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_review, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]

        Glide.with(context)
            .load(review.image)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.image)
        holder.user.text = review.title

        holder.text.text = review.text
        holder.date.text = review.createdAt
        holder.rating.text = review.rating
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById(R.id.review_user_image) as ImageView
        val user = itemView.findViewById(R.id.review_user_name) as TextView
        val text = itemView.findViewById(R.id.review_text) as TextView
        val date = itemView.findViewById(R.id.review_date) as TextView
        val rating = itemView.findViewById(R.id.review_rating) as TextView
    }

}