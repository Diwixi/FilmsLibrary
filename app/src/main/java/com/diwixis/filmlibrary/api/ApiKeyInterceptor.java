package com.diwixis.filmlibrary.api;

import android.support.annotation.NonNull;

import com.diwixis.filmlibrary.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class ApiKeyInterceptor implements Interceptor{

    private final String authToken;

    private ApiKeyInterceptor() {
        authToken = Constants.ACCESS_TOKEN;
    }

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + authToken)
                .build();
        return chain.proceed(request);
    }
}
