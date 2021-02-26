package com.diwixis.feature_movie_list.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.diwixis.feature_movie_list.presentation.movieList.MovieListFragment

internal class ViewPagerFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val types = listOf(
        MovieListFragment.MoviesMode.TOP,
        MovieListFragment.MoviesMode.POP
    )

    override fun createFragment(position: Int): Fragment = MovieListFragment().apply {
        arguments = bundleOf(MovieListFragment.MODE to types[position])
    }

    override fun getItemCount(): Int = types.size
}