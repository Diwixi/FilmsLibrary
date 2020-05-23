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
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_movie.*

/**
 * Created by Diwixis on 20.04.2017.
 */
class MovieActivity : AppCompatActivity((R.layout.activity_movie)) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //вынести в репозиторий
        //создать презентер
        //в презентере метод Observasble<Movie> repositoryGetMovies
        //view show movie
//        val realm = Realm.getDefaultInstance()
//        val repositories = realm.where(Movie::class.java)
//            .equalTo("id", intent.getIntExtra("RESULT_KEY", -1))
//            .findFirst()
//        val res = realm.copyFromRealm(repositories)
//        val display = windowManager.defaultDisplay
//        val width = display.width
//        Glide.with(poster.context)
//            .load("${Urls.IMAGE_URL}${res.posterPath}")
//            .override(width, width)
//            .centerCrop()
//            .into(poster)
//        originalTitle.text = res.originalTitle
//        overview.text = res.overview
//        popularity.text = res.popularity.toString()
//        relaseDate.text = res.releaseDate
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