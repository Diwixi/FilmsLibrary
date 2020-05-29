package com.diwixis.filmlibrary.presentation.movieDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
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
 * Created by Diwixis on 20.04.2017.
 */
class MovieActivity : AppCompatActivity((R.layout.activity_movie)) {

    private val viewModel by inject<MovieDetailViewModel>()
    private val movieObserver = Observer<Response<Movie>> {
        when (it) {
            is Load -> progressBar.isVisible = true
            is Success -> {
                showMovie(it.value)
                progressBar.isVisible = false
            }
            is Failure -> {
                progressBar.isVisible = false
                //TODO show error
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        motion.transitionToEnd()
        viewModel.movie.observe(this, movieObserver)

        val movieId = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        viewModel.loadMovieById(movieId)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_movie, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.back) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

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