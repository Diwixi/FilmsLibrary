package com.diwixis.filmlibrary.movies_module;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.diwixis.filmlibrary.R;
import com.diwixis.filmlibrary.structures.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.arturvasilov.rxloader.LifecycleHandler;
import ru.arturvasilov.rxloader.LoaderLifecycleHandler;

public class MovieGreedActivity extends AppCompatActivity implements MovieGreedView {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MovieItemAdapter adapter;

    private MovieGreedPresenter presenter;

    List<Movie> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_greed);
        ButterKnife.bind(this);

//        int imageHeight = (getResources().getDisplayMetrics().heightPixels - actionBarHeight - statusBarHeight) / 2;
//        String a = "https://i10.fotocdn.net/s16/249/gallery_xs/232/9365496.jpg";
//        String a = "https://cdn.dribbble.com/users/479355/screenshots/3441681/nothing.png";
//        list = new ArrayList<>();
//        list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));
//        list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));list.add(new Movie(a));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new MovieItemAdapter();
        recyclerView.setAdapter(adapter);

        LifecycleHandler lifecycleHandler = LoaderLifecycleHandler.create(this, getSupportLoaderManager());
        presenter = new MovieGreedPresenter(lifecycleHandler, this);
        presenter.init();
    }

    @Override
    public void showLoad() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoad() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMovie(List<Result> results) {
        adapter.setData(results);
    }
}
