package com.diwixis.feature_movie_list.domain.di

import com.diwixis.feature_movie_list.data.Database
import com.diwixis.feature_movie_list.data.MoviesRepositoryImpl
import com.diwixis.feature_movie_list.data.api.TmdbApi
import com.diwixis.feature_movie_list.domain.repository.MoviesRepository
import com.diwixis.feature_movie_list.presentation.movieList.MovieGreedViewModel
import com.diwixis.feature_movie_list.presentation.moviePreview.MovieDetailViewModel
import com.diwixis.network.appRetrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**
 * There are all modules for koin
 *
 * @author П. Густокашин (Diwixis)
 */

fun injectMovieList() = movieListFeature

private val movieListFeature by lazy {
    loadKoinModules(
        dataModule,
        repositoryModule,
        viewModelModule
    )
}

private val dataModule = module {
    single { Database.create(get()) }
    single { movieApi }
}

private val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

private val viewModelModule = module {
    viewModel { MovieGreedViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}

private val movieApi: TmdbApi = appRetrofit.create(TmdbApi::class.java)