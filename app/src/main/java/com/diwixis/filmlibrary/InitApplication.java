package com.diwixis.filmlibrary;

import android.app.Application;

import com.diwixis.filmlibrary.api.TmdbApi;
import com.diwixis.filmlibrary.repository.RepositoryProvider;


import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class InitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .build();
        Realm.setDefaultConfiguration(configuration);

        TmdbApi.create();

        RepositoryProvider.init();
    }
}
