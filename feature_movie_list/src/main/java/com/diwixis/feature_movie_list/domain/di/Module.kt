package com.diwixis.feature_movie_list.domain.di

import com.diwixis.feature_movie_list.BuildConfig
import com.diwixis.feature_movie_list.data.Database
import com.diwixis.feature_movie_list.data.MoviesRepositoryImpl
import com.diwixis.feature_movie_list.data.api.TmdbApi
import com.diwixis.feature_movie_list.domain.repository.MoviesRepository
import com.diwixis.feature_movie_list.presentation.movieList.MovieGreedViewModel
import com.diwixis.feature_movie_list.presentation.moviePreview.MovieDetailViewModel
import com.diwixis.network.Network.createNetworkClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * There are all modules for koin
 *
 * @author П. Густокашин (Diwixis)
 */

public fun injectMovieList() = movieListFeature

private val movieListFeature by lazy {
    loadKoinModules(
        dataModule,
        repositoryModule,
        viewModelModule
    )
}

val dataModule = module {
    single { Database.create(get()) }
    single { movieApi }
}

val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { MovieGreedViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}

private val movieRetrofit: Retrofit
    get() = createNetworkClient(BuildConfig.API_BASE_URL)

private val movieApi: TmdbApi = movieRetrofit.create(TmdbApi::class.java)