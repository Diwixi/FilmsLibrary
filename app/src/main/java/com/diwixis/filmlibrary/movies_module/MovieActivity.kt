package com.diwixis.filmlibrary.movies_module

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.diwixis.filmlibrary.R
import com.diwixis.filmlibrary.api.Urls
import com.diwixis.filmlibrary.repository.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie.*
import org.koin.android.ext.android.inject

/**
 * Created by Diwixis on 20.04.2017.
 */
class MovieActivity : AppCompatActivity((R.layout.activity_movie)) {

    private val repository by inject<MoviesRepository>()

    private val rxDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.getIntExtra("RESULT_KEY", -1)
        repository.getmovieById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { results ->
                    showMovie(results)
                },
                {
                    it.toString()
                }
            ).addTo(rxDisposables)


    }

    private fun showMovie(movie: Movie) {
        Glide.with(poster)
            .load("${movie.posterPath}")
            .centerCrop()
            .into(poster)
        originalTitle.text = movie.originalTitle
        overview.text = movie.overview
        popularity.text = movie.popularity.toString()
        relaseDate.text = movie.releaseDate
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
        fun startActivity(
            activity: Activity,
            movie: Movie
        ) {
            val intent = Intent(activity, MovieActivity::class.java)
            intent.putExtra("RESULT_KEY", movie.id)
            activity.startActivity(intent)
        }
    }
}