package com.diwixis.filmlibrary.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class LogginInterceptor implements  Interceptor{
    private final Interceptor mLoggingInterceptor;

    private LogginInterceptor() {
        mLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @NonNull
    public static Interceptor create() {
        return new LogginInterceptor();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return mLoggingInterceptor.intercept(chain);
    }
}
