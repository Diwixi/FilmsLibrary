package com.diwixis.filmlibrary.repository

import com.diwixis.filmlibrary.Params
import com.diwixis.filmlibrary.api.TmdbApi
import com.diwixis.filmlibrary.data.Database
import com.diwixis.filmlibrary.data.map
import com.diwixis.filmlibrary.movies_module.Movie
import io.reactivex.Single

/**
 * Created by Diwixis on 19.04.2017.
 */
class MoviesRepositoryImpl(
    private val db: Database,
    private val api: TmdbApi
) : MoviesRepository {

    override fun getTopRateMovies() = api.getTopRatedMovies(Params.getMovieParams())
        .map { it.movies }
        .doOnSuccess { movies -> db.movieDao().insertAll(movies) }
        .map { it.map() }
        .onErrorResumeNext {
            db.movieDao().getAll().map { it.map() }
        }

    override fun getmovieById(movieId: Int) = db.movieDao().getById(movieId).map { it.map() }

    //    override fun movies(isTopRated: Boolean): Observable<List<Result>> {
//        val tmdbService: TmdbApi = TmdbApi.getTmdbService()
//        val observable: Observable<Movies?>?
//        observable = if (isTopRated) {
//            tmdbService.getTopRatedMovies(Params.getMovieParams())
//        } else {
//            tmdbService.getPopularMovies(Params.getMovieParams())
//        }
//        return observable
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map<RealmList<Result>>(Movies::results)
//            .doOnNext { results: RealmList<Result>? ->
//                Realm.getDefaultInstance().executeTransaction { realm: Realm ->
//                    realm.delete(Result::class.java)
//                    realm.insert(results)
//                }
//            }
//            .onErrorResumeNext { throwable: Throwable? ->
//                val realm = Realm.getDefaultInstance()
//                val repositories = realm.where(Result::class.java).findAll()
//                return Observable.just<List<Result>>(realm.copyFromRealm(repositories))
//            }
//    }
}