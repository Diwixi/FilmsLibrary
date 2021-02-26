package com.diwixis.feature_movie_list.presentation.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diwixis.feature_movie_list.databinding.FragmentMovieListBinding
import com.diwixis.feature_movie_list.domain.utils.Failure
import com.diwixis.feature_movie_list.domain.utils.Load
import com.diwixis.feature_movie_list.domain.utils.Response
import com.diwixis.feature_movie_list.domain.utils.Success
import com.diwixis.feature_movie_list.presentation.Movie
import com.diwixis.feature_movie_list.presentation.moviePreview.MoviePreviewDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private val viewModel by viewModel<MovieGreedViewModel>()

    private val clickListener = object : MovieItemAdapter.IOnItemClick {
        override fun onItemClick(movie: Movie) {
            MoviePreviewDialogFragment.open(childFragmentManager, movie)
        }
    }

    private val errorObserver = Observer<Response<List<Movie>>> { response ->
        binding.progressBar.isVisible = false
        //TODO show error
    }

    private val listMovieObserver = Observer<Response<List<Movie>>> { response ->
        when (response) {
            is Load -> binding.progressBar.isVisible = true
            is Success -> {
                binding.progressBar.isVisible = false
                val adapter = (binding.recycler.adapter as MovieItemAdapter)
                val newList = adapter.currentList.toMutableList()
                newList.addAll(response.value)
                adapter.submitList(newList)
            }
            is Failure -> {
                binding.progressBar.isVisible = false
                //TODO show error
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mode = arguments?.get(MODE) as MoviesMode
        with(binding.recycler) {
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
}