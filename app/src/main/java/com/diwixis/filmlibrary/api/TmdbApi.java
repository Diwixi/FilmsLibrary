package com.diwixis.filmlibrary.api;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class TmdbApi {
    private static OkHttpClient sClient;

    private static volatile TmdbApiService sService;

    public static TmdbApiService getTmdbService(){
        TmdbApiService service = sService;
        if (service == null) {
            synchronized (TmdbApi.class) {
                service = sService;
                if (service == null) {
                    service = sService = buildRetrofit().create(TmdbApiService.class);
                }
            }
        }
        return service;
    }

    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Urls.BASE)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (TmdbApi.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }

    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(LogginInterceptor.create())
                .addInterceptor(ApiKeyInterceptor.create())
                .build();
    }

    public static void create() {
        sService = buildRetrofit().create(TmdbApiService.class);
    }
}
