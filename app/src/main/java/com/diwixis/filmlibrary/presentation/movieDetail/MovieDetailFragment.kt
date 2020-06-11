package com.diwixis.filmlibrary.presentation.movieDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Failure
import com.diwixis.filmlibrary.api.Load
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.api.Success
import com.diwixis.filmlibrary.presentation.Movie
import kotlinx.android.synthetic.main.activity_movie.*
import org.koin.android.ext.android.inject

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

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

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_movie, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.back) {
//            onBackPressed()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    companion object {
        const val EXTRA_MOVIE_ID = "com.diwixis.filmlibrary.presentation.movieDetail.EXTRA_MOVIE_ID"

        fun startActivity(
            activity: Activity,
            movie: Movie
        ) {
            val intent = Intent(activity, MovieActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movie.id)
            }
            activity.startActivity(intent)
        }
    }

}