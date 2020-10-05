package com.diwixis.filmlibrary.presentation.movieList

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.domain.utils.Failure
import com.diwixis.filmlibrary.domain.utils.Load
import com.diwixis.filmlibrary.domain.utils.Response
import com.diwixis.filmlibrary.domain.utils.Success
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.presentation.movieDetail.MovieDetailDialogFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.koin.android.ext.android.inject

class MovieListFragment : Fragment(R.layout.fragment_movie_list),
    SwipeRefreshLayout.OnRefreshListener {

    private val viewModel by inject<MovieGreedViewModel>()

    private val clickListener = object : MovieItemAdapter.IOnItemClick {
        override fun onItemClick(movie: Movie) {
            MovieDetailDialogFragment.open(childFragmentManager, movie)
        }
    }

    private val errorObserver = Observer<Response<List<Movie>>> { response ->
        response.toString()
    }

    private val listMovieObserver = Observer<Response<List<Movie>>> { response ->
        when (response) {
            is Load -> progressBar.isVisible = true
            is Success -> {
                progressBar.isVisible = false
                val adapter = (recycler.adapter as MovieItemAdapter)
                val newList = adapter.currentList.toMutableList()
                newList.addAll(response.value)
                adapter.submitList(newList)
            }
            is Failure -> {
                progressBar.isVisible = false
                //TODO add error processor
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mode = arguments?.get(MODE) as MoviesMode
        with(recycler) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = MovieItemAdapter().apply {
                setClickListener(clickListener)
            }
            addOnScrollListener(
                viewModel.getPaginationListener(layoutManager as LinearLayoutManager, mode)
            )
        }
        with(viewModel) {
            when (mode) {
                MoviesMode.TOP -> loadTopRateMovies().observe(viewLifecycleOwner, listMovieObserver)
                MoviesMode.POP -> loadPopularMovies().observe(viewLifecycleOwner, listMovieObserver)
            }
            errorLiveData.observe(viewLifecycleOwner, errorObserver)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    enum class MoviesMode(val fullName: String) { TOP("Top rates"), POP("Popular") }

    companion object {
        const val MODE = "mode"
    }

    override fun onRefresh() {

    }
}