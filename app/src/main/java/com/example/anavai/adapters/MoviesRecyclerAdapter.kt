package com.example.anavai.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.anavai.R
import com.example.anavai.fragments.MoviesFragmentDirections
import com.example.anavai.models.Movie
import com.example.anavai.utils.loadImage
import kotlinx.android.synthetic.main.recycler_item_movie.view.*


class MoviesRecyclerAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.title
        holder.description.text = movie.text
        holder.image.loadImage(movie.image)

        holder.container.setOnClickListener {
//            it.findNavController().navigate(R.id.navigate_singleMedia_to_MusicMedia)
            val action: NavDirections =
                MoviesFragmentDirections.navigateSingleMediaToTextMedia(movie.id)
            it.findNavController().navigate(action)
        }
    }

    fun updateMovieList(movieList: ArrayList<Movie>) {
        val currentMovies: ArrayList<Movie> = ArrayList(movies)
        currentMovies.clear()
        currentMovies.addAll(movieList)
        this.notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById(R.id.media_instance_title) as TextView
        val image = itemView.findViewById(R.id.media_instance_image) as ImageView
        val description = itemView.findViewById(R.id.media_instance_description) as TextView
        val container: CardView = itemView.media_instance_container
    }

}