package com.diwixis.filmlibrary.domain.di

import com.diwixis.filmlibrary.BuildConfig.API_BASE_URL
import com.diwixis.filmlibrary.data.api.Network.createNetworkClient
import com.diwixis.filmlibrary.data.api.TmdbApi
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.presentation.moviePreview.MovieDetailViewModel
import com.diwixis.filmlibrary.presentation.movieList.MovieGreedViewModel
import com.diwixis.filmlibrary.domain.repository.MoviesRepository
import com.diwixis.filmlibrary.data.MoviesRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * There are all modules for koin
 *
 * @author П. Густокашин (Diwixis)
 */
val dataModule = module {
    single { Database.create(get()) }
    single { movieApi }
}

val repositoryModule = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    factory { MovieGreedViewModel(get()) }
    factory { MovieDetailViewModel(get()) }
}

private val movieRetrofit: Retrofit
    get() = createNetworkClient(API_BASE_URL)

private val movieApi: TmdbApi = movieRetrofit.create(TmdbApi::class.java)