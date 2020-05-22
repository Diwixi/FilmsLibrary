package com.diwixis.filmlibrary.movies_module

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.data.Result
import com.diwixis.filmlibrary.movies_module.MovieItemAdapter.IOnItemClick
import kotlinx.android.synthetic.main.activity_movie_greed.*

class MovieGreedActivity : AppCompatActivity(R.layout.activity_movie_greed), MovieGreedView {
    private var presenter: MovieGreedPresenter? = null
    var width = 0
    private val clickListener = object : IOnItemClick {
        override fun onItemClick(result: Result?) {
            MovieActivity.startActivity(this@MovieGreedActivity, result!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(this, 2)
        val adapter = MovieItemAdapter()
        adapter.setClickListener(clickListener)
        recycler.adapter = adapter
        presenter = MovieGreedPresenter(this)
        presenter!!.init(false)
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
                presenter!!.update(false)
                true
            }
            R.id.item_top_rated -> {
                presenter!!.update(true)
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

    override fun showMovie(results: List<Result>) {
        (recycler.adapter as MovieItemAdapter).setData(results, width / 3)
    }
}