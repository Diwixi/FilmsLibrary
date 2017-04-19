package com.diwixis.filmlibrary.movies_module;

import com.diwixis.filmlibrary.R;
import com.diwixis.filmlibrary.repository.RepositoryProvider;

import ru.arturvasilov.rxloader.LifecycleHandler;


/**
 * Created by Diwixis on 19.04.2017.
 */

class MovieGreedPresenter {

    private final LifecycleHandler lifecycleHandler;
    private final MovieGreedView movieGreedView;

    MovieGreedPresenter(LifecycleHandler lifecycleHandler, MovieGreedView movieGreedView) {
        this.lifecycleHandler = lifecycleHandler;
        this.movieGreedView = movieGreedView;
    }

    void init() {
        RepositoryProvider.providerRepository()
                .movies()
                .doOnSubscribe(movieGreedView::showLoad)
                .doOnTerminate(movieGreedView::hideLoad)
                .compose(lifecycleHandler.reload(R.id.request))
                .subscribe(movieGreedView::showMovie);
    }

    void update() {
        RepositoryProvider.providerRepository()
                .movies()
                .compose(lifecycleHandler.reload(R.id.request))
                .subscribe(movieGreedView::showMovie);
    }
}
