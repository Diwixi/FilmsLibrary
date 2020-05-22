package com.diwixis.filmlibrary.movies_module;

import com.diwixis.filmlibrary.repository.RepositoryProvider;


/**
 * Created by Diwixis on 19.04.2017.
 */

class MovieGreedPresenter {
    private final MovieGreedView movieGreedView;

    MovieGreedPresenter(MovieGreedView movieGreedView) {
        this.movieGreedView = movieGreedView;
    }

    void init(boolean isTopRated) {
        RepositoryProvider.providerRepository()
                .movies(isTopRated)
                .doOnSubscribe(movieGreedView::showLoad)
                .doOnTerminate(movieGreedView::hideLoad)
                .subscribe(movieGreedView::showMovie);
    }

    void update(boolean isTopRated) {
        RepositoryProvider.providerRepository()
                .movies(isTopRated)
                .subscribe(movieGreedView::showMovie);
    }
}
