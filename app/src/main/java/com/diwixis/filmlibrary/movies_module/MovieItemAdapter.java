package com.diwixis.filmlibrary.movies_module;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.diwixis.filmlibrary.R;
import com.diwixis.filmlibrary.structures.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diwixis on 18.04.2017.
 */

class MovieItemAdapter extends RecyclerView.Adapter<MovieItemAdapter.ViewHolder> {

    private List<Result> movieList = new ArrayList<>();

    void setData(List<Result> list){
        movieList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result movie = movieList.get(position);
        Glide.with(holder.image.getContext())
                .load(movie.getPosterPath())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            //убрать
            layoutParams.height = 80;
            layoutParams.width = 100;
            //
            image.requestLayout();
        }
    }
}
