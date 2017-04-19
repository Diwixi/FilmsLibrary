package com.diwixis.filmlibrary.movies_module;

import com.diwixis.filmlibrary.structures.Result;

import java.util.List;

/**
 * Created by Diwixis on 19.04.2017.
 */

interface MovieGreedView {
    void showLoad();
    void hideLoad();
    void showMovie(List<Result> results);
}
