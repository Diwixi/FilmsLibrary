package com.diwixis.filmlibrary.domain.di

import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.data.MoviesRepositoryImpl
import com.diwixis.filmlibrary.data.datasource.LocalDataSource
import com.diwixis.filmlibrary.data.datasource.RemoteDataSource
import com.diwixis.filmlibrary.domain.MoviesRepository
import com.diwixis.filmlibrary.domain.usecases.GetNowPlayingMoviesUseCase
import com.diwixis.filmlibrary.domain.usecases.GetPopMoviesUseCase
import com.diwixis.filmlibrary.domain.usecases.GetTopMoviesUseCase
import com.diwixis.filmlibrary.domain.usecases.MainScreenUseCase
import com.diwixis.filmlibrary.domain.viewmodels.MainViewModel
import com.pg.network.BuildConfig.API_BASE_URL
import com.pg.network.Network.createNetworkClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
}

val sourceModule = module {
    singleOf(::LocalDataSource)
    singleOf(::RemoteDataSource)
    single { Database.create(get()) }
    single<TmdbApi> { createNetworkClient(API_BASE_URL).create(TmdbApi::class.java) }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    factoryOf(::GetTopMoviesUseCase)
    factoryOf(::GetPopMoviesUseCase)
    factoryOf(::GetNowPlayingMoviesUseCase)
    factoryOf(::MainScreenUseCase)
}
