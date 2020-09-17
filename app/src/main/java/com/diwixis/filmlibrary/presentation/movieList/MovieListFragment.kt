package com.diwixis.filmlibrary.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.domain.utils.Failure
import com.diwixis.filmlibrary.domain.utils.Load
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.domain.utils.Success
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
            layoutManager = LinearLayoutManager(activity)
            adapter = MovieItemAdapter().apply {
                setClickListener(clickListener)
            }
        }
        with(viewModel) {
            when (arguments?.get(MODE)) {
                MoviesMode.TOP -> loadTopRateMovies().observe(viewLifecycleOwner, listMovieObserver)
                MoviesMode.POP -> loadPopularMovies().observe(viewLifecycleOwner, listMovieObserver)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    enum class MoviesMode(val fullName: String) { TOP("Top rates"), POP("Popular") }

    companion object {
        const val MODE = "mode"
    }
}