package com.diwixis.feature_movie_list.presentation.moviePreview

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import coil.load
import com.diwixis.feature_movie_list.R
import com.diwixis.feature_movie_list.databinding.FragmentMoviePreviewBinding
import com.diwixis.feature_movie_list.domain.utils.*
import com.diwixis.feature_movie_list.presentation.Movie
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class MoviePreviewDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentMoviePreviewBinding
    private val viewModel by viewModel<MovieDetailViewModel>()

    private val errorObserver = Observer<Response<Movie>> { response ->
        binding.progressBar.isVisible = false
        //TODO show error
    }
    private val movieObserver = Observer<Response<Movie>> {
        when (it) {
            is Load -> binding.progressBar.isVisible = true
            is Success -> {
                binding.progressBar.isVisible = false
                showMovie(it.value)
            }
            is Failure -> {
                binding.progressBar.isVisible = false
                //TODO show error
            }
        }
    }

    override fun getTheme() = R.style.BaseBottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        bottomDialog.setOnShowListener { dialog ->
            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
        }

        return bottomDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePreviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(EXTRA_MOVIE_ID, 0)?.let { movieId ->
            viewModel.loadMovieById(movieId).observe(viewLifecycleOwner, movieObserver)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)

        binding.showMoreButton.setOnClick { context?.toast("in progress..") }
    }

    @SuppressLint("SetTextI18n")
    private fun showMovie(movie: Movie) {
        with(binding) {
            originalTitle.text = movie.title
            overview.text = "${getString(R.string.overview)} ${movie.overview}"
            popularity.text = "${getString(R.string.popularity_text)} ${movie.popularity}"
            releaseDate.text =
                "${getString(R.string.day_out)} ${movie.releaseDate?.replace("-", ".")}"

            poster.load(movie.posterPath)
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID =
            "com.diwixis.feature_movie_list.presentation.movieDetail.EXTRA_MOVIE_ID"

        fun open(fragmentManager: FragmentManager, movie: Movie) {
            val mBottomSheetDialog = MoviePreviewDialogFragment().apply {
                arguments = bundleOf(EXTRA_MOVIE_ID to movie.id)
            }
            mBottomSheetDialog.show(fragmentManager, "MovieDetailDialogFragment")
        }
    }
}