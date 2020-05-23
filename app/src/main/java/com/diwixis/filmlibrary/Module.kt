package com.diwixis.filmlibrary

import com.diwixis.filmlibrary.Network.createNetworkClient
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.api.Urls
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.movies_module.MovieGreedPresenter
import com.diwixis.filmlibrary.repository.MoviesRepository
import com.diwixis.filmlibrary.repository.MoviesRepositoryImpl
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

val presenterModule = module {
    single { MovieGreedPresenter(get()) }
}

private val movieRetrofit: Retrofit
    get() = createNetworkClient(Urls.BASE)

private val movieApi: TmdbApi = movieRetrofit.create(TmdbApi::class.java)