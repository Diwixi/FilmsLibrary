package com.diwixis.feature_movie_list.presentation.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.diwixis.feature_movie_list.R
import com.diwixis.feature_movie_list.databinding.ItemMovieBinding
import com.diwixis.feature_movie_list.domain.utils.setOnClick
import com.diwixis.feature_movie_list.presentation.Movie

internal class MovieItemAdapter :
    ListAdapter<Movie, MovieItemAdapter.ViewHolder>(MOVIE_COMPARATOR) {
    private var clickListener: IOnItemClick? = null

    fun setClickListener(listener: IOnItemClick?) {
        clickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemMovieBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface IOnItemClick {
        fun onItemClick(movie: Movie)
    }

    internal inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                root.animation =
                    AnimationUtils.loadAnimation(itemView.context, R.anim.recycler_anim)
                root.setOnClick(1000) {
                    clickListener?.onItemClick(getItem(adapterPosition))
                }
                image.requestLayout()
                itemTitle.text = movie.title
                image.load(movie.posterPath) {
                    transformations(RoundedCornersTransformation(16f))
                }
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