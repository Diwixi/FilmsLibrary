package com.diwixis.filmlibrary.repository;

import com.diwixis.filmlibrary.Constants;
import com.diwixis.filmlibrary.Params;
import com.diwixis.filmlibrary.api.TmdbApi;
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
    public Observable<List<Result>> movies() {
        return TmdbApi.getTmdbService()
                .getMovies(Params.getMovieParams())
                .map(Movies::getResults)
                .doOnNext(results -> Realm.getDefaultInstance()
                        .executeTransaction(realm -> realm.copyToRealmOrUpdate(results)))
                .onErrorResumeNext(throwable -> {
                    Realm realm = Realm.getDefaultInstance();
                    RealmResults<Result> repositories = realm.where(Result.class).findAll();
                    return Observable.just(realm.copyFromRealm(repositories));
                })
                .compose(RxUtils.async());
    }
}
