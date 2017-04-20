package com.diwixis.filmlibrary.repository;

import com.diwixis.filmlibrary.Params;
import com.diwixis.filmlibrary.api.TmdbApi;
import com.diwixis.filmlibrary.api.TmdbApiService;
import com.diwixis.filmlibrary.structures.Movies;
import com.diwixis.filmlibrary.structures.Result;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.arturvasilov.rxloader.RxUtils;
import rx.Observable;

/**
 * Created by Diwixis on 19.04.2017.
 */

public class Repository implements IRepository{
    @Override
    public Observable<List<Result>> movies(boolean isTopRated) {
        TmdbApiService tmdbService = TmdbApi.getTmdbService();
        Observable<Movies> observable;
        if (isTopRated){
            observable = tmdbService.getTopRatedMovies(Params.getMovieParams());
        } else {
            observable = tmdbService.getPopularMovies(Params.getMovieParams());
        }
        return observable
                .map(Movies::getResults)
                .doOnNext(results -> Realm.getDefaultInstance()
                    .executeTransaction(realm -> {
                        realm.delete(Result.class);
                        realm.insert(results);
                    }))
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Result> repositories = realm.where(Result.class).findAll();
                    return Observable.just(realm.copyFromRealm(repositories));
                })
                .compose(RxUtils.async());
    }
}
