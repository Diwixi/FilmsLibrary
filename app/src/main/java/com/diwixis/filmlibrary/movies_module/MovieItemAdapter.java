package com.diwixis.filmlibrary.movies_module;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.diwixis.filmlibrary.R;
import com.diwixis.filmlibrary.api.Urls;
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
    private IOnItemClick clickListener = null;
    private int width = 0;

    void setData(List<Result> list, int width){
        movieList = list;
        notifyDataSetChanged();
        this.width = width;
    }

    void setClickListener(IOnItemClick listener){
        clickListener = listener;
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
                .load(Urls.IMAGE_URL+movie.getPosterPath())
                .asBitmap()
                .override(width, width)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public interface IOnItemClick{
        void onItemClick(Result result);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.image)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            image.requestLayout();
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(movieList.get(getAdapterPosition()));
        }
    }
}
