package com.diwixis.filmlibrary

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.diwixis.filmlibrary.presentation.movieList.MovieListFragment
import com.diwixis.filmlibrary.presentation.movieList.MovieListFragment.Companion.MODE
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_movies_pager.*

class MovieListPager : Fragment(R.layout.fragment_movies_pager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager2.adapter = ViewPagerFragmentAdapter(requireActivity())
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = MovieListFragment.MoviesMode.values()[position].fullName
        }.attach()
    }
}

class ViewPagerFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    private val types = listOf(
        MovieListFragment.MoviesMode.TOP,
        MovieListFragment.MoviesMode.POP
    )

    override fun createFragment(position: Int): Fragment = MovieListFragment().apply {
        arguments = bundleOf(MODE to types[position])
    }

    override fun getItemCount(): Int = types.size
}