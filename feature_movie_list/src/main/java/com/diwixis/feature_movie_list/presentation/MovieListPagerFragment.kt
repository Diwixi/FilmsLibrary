package com.diwixis.feature_movie_list.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.diwixis.feature_movie_list.R
import com.diwixis.feature_movie_list.databinding.FragmentMoviesPagerBinding
import com.diwixis.feature_movie_list.presentation.movieList.MovieListFragment
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

