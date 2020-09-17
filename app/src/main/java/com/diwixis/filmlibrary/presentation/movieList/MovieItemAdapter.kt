package com.diwixis.filmlibrary.presentation.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.domain.utils.setOnClick
import kotlinx.android.synthetic.main.item_movie.view.*

internal class MovieItemAdapter : RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    private var movieList: List<Movie> = emptyList()
    private var clickListener: IOnItemClick? = null

    fun setData(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(movieList[position])
    }

    override fun getItemCount() = movieList.size

    interface IOnItemClick {
        fun onItemClick(movie: Movie)
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            itemView.setOnClick(1000) {
                clickListener?.onItemClick(movieList[adapterPosition])
            }
            itemView.image.requestLayout()
            itemView.itemTitle.text = movie.title

            itemView.image.load(movie.posterPath) {
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }

    companion object {
        val MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
                return null
            }
        }
    }
}