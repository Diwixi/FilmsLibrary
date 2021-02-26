package com.diwixis.feature_movie_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.diwixis.feature_movie_list.R
import com.diwixis.feature_movie_list.databinding.FragmentMoviesPagerBinding
import com.diwixis.feature_movie_list.presentation.movieList.MovieListFragment
import com.diwixis.feature_movie_list.presentation.movieList.MovieListFragment.Companion.MODE
import com.google.android.material.tabs.TabLayoutMediator

class MovieListPagerFragment : Fragment(R.layout.fragment_movies_pager) {

    private lateinit var binding: FragmentMoviesPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesPagerBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            viewPager2.adapter = ViewPagerFragmentAdapter(requireActivity())
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = MovieListFragment.MoviesMode.values()[position].fullName
            }.attach()
        }
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