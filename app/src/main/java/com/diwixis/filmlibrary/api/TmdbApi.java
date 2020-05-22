package com.diwixis.filmlibrary.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class TmdbApi {
    private static volatile TmdbApiService mService;

    public static TmdbApiService getTmdbService() {
        TmdbApiService service = mService;
        if (service == null) {
            synchronized (TmdbApi.class) {
                service = mService;
                if (service == null) {
                    service = mService = buildRetrofit().create(TmdbApiService.class);
                }
            }
        }
        return service;
    }

    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Urls.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    //заменить на провайд
    public static void create() {
        mService = buildRetrofit().create(TmdbApiService.class);
    }
}
