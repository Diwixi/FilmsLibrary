package com.diwixis.filmlibrary.api;

import com.diwixis.filmlibrary.structures.Movies;

import java.util.HashMap;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Diwixis on 18.04.2017.
 */

public interface TmdbApiService {
    @GET(Urls.MOVIE)
    Observable<Movies> getMovies(@QueryMap HashMap<String, String> params);
//            @Query("api_key")String apiKey,
//            @Query("language")String language,
//            @Query("page")int page
//    );
}
