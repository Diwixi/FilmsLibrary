package com.diwixis.filmlibrary.presentation.refactoring

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Failure
import com.diwixis.filmlibrary.api.Load
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.api.Success
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.presentation.movieDetail.MovieActivity
import com.diwixis.filmlibrary.presentation.movieDetail.MovieDetailFragment
import com.diwixis.filmlibrary.presentation.movieDetail.MovieDetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setStyle(
//            DialogFragment.STYLE_NORMAL,
//            R.style.FullScreenDialogStyle
//        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_movie_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movie.observe(viewLifecycleOwner, movieObserver)

        arguments?.getInt(MovieDetailFragment.EXTRA_MOVIE_ID, 0)?.let { movieId ->
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
        fun open(
            fragmentManager: FragmentManager,
            movie: Movie
        ) {
            val mBottomSheetDialog = MovieDetailDialogFragment().apply {
                arguments = Bundle().apply { putInt(MovieActivity.EXTRA_MOVIE_ID, movie.id) }
            }
            mBottomSheetDialog.show(fragmentManager, "MovieDetailDialogFragment")
        }
    }
}