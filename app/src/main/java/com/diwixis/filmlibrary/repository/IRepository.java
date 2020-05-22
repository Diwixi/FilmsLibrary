package com.diwixis.filmlibrary.repository;

import androidx.annotation.NonNull;

import com.diwixis.filmlibrary.data.Result;

import java.util.List;

import rx.Observable;

/**
 * Created by Diwixis on 19.04.2017.
 */

public interface IRepository {
    @NonNull
    Observable<List<Result>> movies(boolean isTopRated);
}
