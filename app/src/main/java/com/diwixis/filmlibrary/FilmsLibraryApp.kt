package com.diwixis.filmlibrary

import android.app.Application
import com.diwixis.filmlibrary.di.dataModule
import com.diwixis.filmlibrary.di.repositoryModule
import com.diwixis.filmlibrary.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FilmsLibraryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FilmsLibraryApp)
            modules(dataModule, repositoryModule, viewModelModule)
        }
    }
}