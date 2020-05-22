package com.diwixis.filmlibrary.api;

import com.diwixis.filmlibrary.data.Movies;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Diwixis on 18.04.2017.
 */

public interface TmdbApiService {
    @GET(Urls.MOVIE_POPULAR)
    Observable<Movies> getPopularMovies(@QueryMap HashMap<String, String> params);

    @GET(Urls.MOVIE_TOP_RATE)
    Observable<Movies> getTopRatedMovies(@QueryMap HashMap<String, String> params);
}
