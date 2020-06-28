package com.diwixis.filmlibrary.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Failure
import com.diwixis.filmlibrary.api.Load
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.api.Success
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.presentation.movieDetail.MovieDetailDialogFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.ext.android.inject

/**
 * 03.06.2020
 * @author П. Густокашин (Diwixis)
 */
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val viewModel by inject<MovieGreedViewModel>()

    private val clickListener = object : MovieItemAdapter.IOnItemClick {
        override fun onItemClick(movie: Movie) {
            MovieDetailDialogFragment.open(childFragmentManager, movie)
        }
    }

    private val listMovieObserver = Observer<Response<List<Movie>>> {
        when (it) {
            is Load -> progressBar.isVisible = true
            is Success -> {
                progressBar.isVisible = false
                (recycler.adapter as MovieItemAdapter).setData(it.value)
            }
            is Failure -> {
                progressBar.isVisible = false
                //TODO add error processor
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(recycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = MovieItemAdapter().apply { setClickListener(clickListener) }
        }
        with(viewModel) {
            movies.observe(viewLifecycleOwner, listMovieObserver)
            when (arguments?.get("mode")) {
                MoviesMode.TOP -> loadTopRateMovies()
                MoviesMode.POP -> loadPopularMovies()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    enum class MoviesMode(value: String) { TOP("Top"), POP("Popular") }
}