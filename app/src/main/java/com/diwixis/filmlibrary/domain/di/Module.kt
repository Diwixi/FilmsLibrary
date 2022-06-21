package com.diwixis.filmlibrary.domain.di

import com.diwixis.filmlibrary.data.Database
import com.pg.remote.MoviesRepositoryImpl
import com.pg.feature_network.LocalDataSource
import com.pg.remote.RemoteDataSource
import com.diwixis.filmlibrary.domain.usecases.*
import com.pg.feature_preview.MoviesPreviewViewModel
import com.pg.feature_details.presentation.MovieDetailsViewModel
import com.diwixis.filmlibrary.features.list.MovieListViewModel
import com.pg.feature_details.usecase.MovieDetailsUseCase
import com.pg.feature_preview.MainUseCase
import com.pg.network.BuildConfig.API_BASE_URL
import com.pg.feature_network.Network.createNetworkClient
import com.pg.remote.GetNowPlayingMoviesUseCase
import com.pg.remote.GetPopMoviesUseCase
import com.pg.remote.GetTopMoviesUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MoviesPreviewViewModel)
    viewModelOf(::MovieListViewModel)
    viewModelOf(::MovieDetailsViewModel)
}

val sourceModule = module {
    singleOf(::LocalDataSource)
    singleOf(::RemoteDataSource)
    single { Database.create(get()) }
    single<TmdbApi> { createNetworkClient(API_BASE_URL).create(com.pg.remote.TmdbApi::class.java) }
    single<MoviesRepository> { com.pg.remote.MoviesRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factoryOf(::GetTopMoviesUseCase)
    factoryOf(::GetPopMoviesUseCase)
    factoryOf(::GetNowPlayingMoviesUseCase)
    factoryOf(::MainUseCase)
    factoryOf(::MovieListUseCase)
    factoryOf(::MovieDetailsUseCase)
}
