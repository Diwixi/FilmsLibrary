package com.diwixis.filmlibrary.presentation.movieList

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Failure
import com.diwixis.filmlibrary.api.Load
import com.diwixis.filmlibrary.api.Response
import com.diwixis.filmlibrary.api.Success
import com.diwixis.filmlibrary.presentation.Movie
import com.diwixis.filmlibrary.presentation.movieDetail.MovieActivity
import com.diwixis.filmlibrary.presentation.movieList.MovieItemAdapter.IOnItemClick
import kotlinx.android.synthetic.main.activity_movie_greed.*
import org.koin.android.ext.android.inject

class MovieGreedActivity : AppCompatActivity(R.layout.activity_movie_greed) {
    private val viewModel by inject<MovieGreedViewModel>()

    private val clickListener = object : IOnItemClick {
        override fun onItemClick(movie: Movie) {
            MovieActivity.startActivity(this@MovieGreedActivity, movie)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(recycler) {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@MovieGreedActivity, 2)
            adapter = MovieItemAdapter().apply { setClickListener(clickListener) }
        }
        with(viewModel) {
            movies.observe(this@MovieGreedActivity, listMovieObserver)
            loadPopularMovies()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_popular -> {
                viewModel.loadPopularMovies()
                true
            }
            R.id.item_top_rated -> {
                viewModel.loadTopRateMovies()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}