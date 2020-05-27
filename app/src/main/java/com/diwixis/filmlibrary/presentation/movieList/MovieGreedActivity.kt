package com.diwixis.filmlibrary.presentation.movieList

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.presentation.*
import com.diwixis.filmlibrary.presentation.movieDetail.MovieActivity
import com.diwixis.filmlibrary.presentation.movieList.MovieItemAdapter.IOnItemClick
import kotlinx.android.synthetic.main.activity_movie_greed.*
import org.koin.android.ext.android.inject

class MovieGreedActivity : AppCompatActivity(R.layout.activity_movie_greed),
    MovieGreedView {
    private val presenter by inject<MovieGreedPresenter>()
    private val viewModel by inject<MovieGreedViewModel>()
    var width = 0
    private val clickListener = object : IOnItemClick {
        override fun onItemClick(movie: Movie?) {
            MovieActivity.startActivity(
                this@MovieGreedActivity,
                movie!!
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(this, 2)
        val adapter =
            MovieItemAdapter()
        adapter.setClickListener(clickListener)
        recycler.adapter = adapter
        presenter.movieGreedView = this
        presenter.showPopularMovies()
        val display = windowManager.defaultDisplay
        width = display.width
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_popular -> {
                presenter.showPopularMovies()
                true
            }
            R.id.item_top_rated -> {
                presenter.showTopRateMovies()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun showLoad() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoad() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMovie(movieList: List<Movie>) {
        (recycler.adapter as MovieItemAdapter).setData(movieList, width / 3)
    }
}