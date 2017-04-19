package com.diwixis.filmlibrary.api;

import com.diwixis.filmlibrary.structures.Movies;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Diwixis on 18.04.2017.
 */

public interface TmdbApiService {
    @GET(Urls.MOVIE)
    Observable<Movies> getMovies(
            @Query("api_key")String apiKey,
            @Query("language")String language,
            @Query("page")int page
    );
}
