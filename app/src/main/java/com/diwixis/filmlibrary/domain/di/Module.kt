package com.diwixis.filmlibrary.domain.di

import android.app.Application
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.data.MoviesRepositoryImpl
import com.diwixis.filmlibrary.domain.MoviesRepository
import com.pg.network.Network.createNetworkClient
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bindProvider
import org.kodein.di.instance

fun initApp(app: Application) {
    DI {
        import(androidXModule(app))
    }
}

val moviesList = DI {
    bindProvider { Database.create(instance()) }
    bindProvider<TmdbApi> { createNetworkClient(API_BASE_URL).create(TmdbApi::class.java) }
    bindProvider<MoviesRepository> { MoviesRepositoryImpl(instance()) }
//    bindProvider { MoviesViewModel(instance()) }
}
