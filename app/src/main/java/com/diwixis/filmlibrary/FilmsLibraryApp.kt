package com.diwixis.filmlibrary

import android.app.Application
import com.diwixis.filmlibrary.domain.di.dataModule
import com.diwixis.filmlibrary.domain.di.repositoryModule
import com.diwixis.filmlibrary.domain.di.viewModelModule
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