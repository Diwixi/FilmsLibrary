package com.diwixis.filmlibrary.api;

import com.diwixis.filmlibrary.structures.Movies;

import java.util.HashMap;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Diwixis on 18.04.2017.
 */

public interface TmdbApiService {
    @GET(Urls.MOVIE)
    Observable<Movies> getMovies(@QueryMap HashMap<String, String> params);

    @GET(Urls.IMAGE)
    Call<ResponseBody> getImage(@Part("file_path") String filePath);
}
