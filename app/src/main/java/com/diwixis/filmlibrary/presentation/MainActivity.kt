package com.diwixis.filmlibrary.presentation

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.diwixis.filmlibrary.R

/**
 *
 *
 * @author П. Густокашин (Diwixis)
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_popular -> {
//                viewModel.loadPopularMovies()
                true
            }
            R.id.item_top_rated -> {
//                viewModel.loadTopRateMovies()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}