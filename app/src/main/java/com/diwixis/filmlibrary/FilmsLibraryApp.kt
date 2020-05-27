package com.diwixis.filmlibrary

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Diwixis on 19.04.2017.
 */
class FilmsLibraryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .rxFactory(RealmObservableFactory())
            .build()
        Realm.setDefaultConfiguration(configuration)

        startKoin {
            androidContext(this@FilmsLibraryApp)
            modules(dataModule, repositoryModule, viewModelModule)
        }
    }
}