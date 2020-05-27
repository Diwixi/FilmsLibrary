package com.diwixis.filmlibrary.presentation.movieList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.presentation.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by Diwixis on 18.04.2017.
 */
internal class MovieItemAdapter :
    RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    private var movieList: List<Movie> = emptyList()
    private var clickListener: IOnItemClick? = null
    private var width = 0
    fun setData(
        list: List<Movie>,
        width: Int
    ) {
        movieList = list
        notifyDataSetChanged()
        this.width = width
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
        fun onItemClick(movie: Movie?)
    }

    internal inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            clickListener?.onItemClick(movieList[adapterPosition])
        }

        fun bind(movie: Movie) {
            itemView.setOnClickListener(this)
            Glide.with(itemView.image)
                .load(movie.posterPath)
                .override(width, width)
                .centerCrop()
                .into(itemView.image)
            itemView.image.requestLayout()
        }
    }
}