package com.diwixis.filmlibrary.movies_module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.diwixis.filmlibrary.R;
import com.diwixis.filmlibrary.api.Urls;
import com.diwixis.filmlibrary.structures.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Diwixis on 20.04.2017.
 */

public class MovieActivity extends AppCompatActivity{
    @BindView(R.id.originalTitle)
    TextView title;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.overview)
    TextView overview;
    @BindView(R.id.popularity)
    TextView popularity;
    @BindView(R.id.relaseDate)
    TextView relaseDate;
    private Result result;

    public static void startActivity(Activity activity, Result result){
        Intent intent = new Intent(activity, MovieActivity.class);
        intent.putExtra("RESULT_KEY", result.getId());
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        Realm realm = Realm.getDefaultInstance();
        Result repositories = realm.where(Result.class).equalTo("id", getIntent().getIntExtra("RESULT_KEY", -1)).findFirst();
        result = realm.copyFromRealm(repositories);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();

        Glide.with(poster.getContext())
                .load(Urls.IMAGE_URL+result.getPosterPath())
                .asBitmap()
                .override(width, width)
                .centerCrop()
                .into(poster);
        title.setText(result.getOriginalTitle());
        overview.setText(result.getOverview());
        popularity.setText(Double.toString(result.getPopularity()));
        relaseDate.setText(result.getReleaseDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back: {
                onBackPressed();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
