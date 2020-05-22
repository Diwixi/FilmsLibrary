package com.diwixis.filmlibrary.movies_module

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Urls
import com.diwixis.filmlibrary.data.Result
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.*

/**
 * Created by Diwixis on 18.04.2017.
 */
internal class MovieItemAdapter :
    RecyclerView.Adapter<MovieItemAdapter.ViewHolder>() {
    private var movieList: List<Result>? = ArrayList()
    private var clickListener: IOnItemClick? = null
    private var width = 0
    fun setData(
        list: List<Result>?,
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
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.bind(movieList!![position])
    }

    override fun getItemCount(): Int {
        return if (movieList == null) 0 else movieList!!.size
    }

    interface IOnItemClick {
        fun onItemClick(result: Result?)
    }

    internal inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
            clickListener!!.onItemClick(movieList!![adapterPosition])
        }

        fun bind(movie: Result) {
            itemView.setOnClickListener(this)
            Glide.with(itemView.image.context)
                .load(Urls.IMAGE_URL + movie.posterPath)
                .override(width, width)
                .centerCrop()
                .into(itemView.image)
            itemView.image.requestLayout()
        }
    }
}