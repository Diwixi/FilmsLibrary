package com.diwixis.filmlibrary.presentation.movieDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Failure
import com.diwixis.filmlibrary.api.Load
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.api.Success
import com.diwixis.filmlibrary.presentation.Movie
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_movie.*
import org.koin.android.ext.android.inject

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class MovieDetailDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by inject<MovieDetailViewModel>()
    private val movieObserver = Observer<Response<Movie>> {
        when (it) {
            is Load -> progressBar.isVisible = true
            is Success -> {
                progressBar.isVisible = false
                showMovie(it.value)
            }
            is Failure -> {
                progressBar.isVisible = false
                //TODO show error
            }
        }
    }

    override fun getTheme() = R.style.BottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movie_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, movieObserver)

        arguments?.getInt(EXTRA_MOVIE_ID, 0)?.let { movieId ->
            viewModel.loadMovieById(movieId)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showMovie(movie: Movie) {
        Glide.with(poster)
            .load("${movie.posterPath}")
            .into(poster)
        originalTitle.text = movie.originalTitle
        overview.text = "${getString(R.string.overview)} ${movie.overview}"
        popularity.text = "${getString(R.string.popularity_text)} ${movie.popularity}"
        relaseDate.text = "${getString(R.string.day_out)} ${movie.releaseDate}"
    }

    companion object {
        const val EXTRA_MOVIE_ID = "com.diwixis.filmlibrary.presentation.movieDetail.EXTRA_MOVIE_ID"

        fun open(
            fragmentManager: FragmentManager,
            movie: Movie
        ) {
            val mBottomSheetDialog = MovieDetailDialogFragment()
                .apply {
                    arguments = bundleOf(MovieActivity.EXTRA_MOVIE_ID to movie.id)
                }
            mBottomSheetDialog.show(fragmentManager, "MovieDetailDialogFragment")
        }
    }
}