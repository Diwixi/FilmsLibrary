package com.diwixis.filmlibrary.repository;

import android.support.annotation.NonNull;

import com.diwixis.filmlibrary.structures.Result;

import java.util.List;

import rx.Observable;

/**
 * Created by Diwixis on 19.04.2017.
 */

public interface IRepository {
    @NonNull
    Observable<List<Result>> movies(boolean isTopRated);
}
