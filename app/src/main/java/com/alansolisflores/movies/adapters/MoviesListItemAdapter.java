package com.alansolisflores.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alansolisflores.movies.R;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.helpers.Config;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesListItemAdapter extends BaseAdapter {

    private List<Movie> movieList;

    private Context context;

    private int layoutId;

    public  MoviesListItemAdapter(List<Movie> movieList, Context context, int layoutId){
        this.movieList = movieList;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int i) {
        return movieList.get(i);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MoviesListItemAdapter.ViewHolder viewHolder;

        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(this.layoutId,null);
            viewHolder = new MoviesListItemAdapter.ViewHolder();

            viewHolder.posterImageView = view.findViewById(R.id.posterImage);
            viewHolder.titleTextView = view.findViewById(R.id.titleTextView);
            viewHolder.releaseDateTextView = view.findViewById(R.id.dateTextView);

            view.setTag(viewHolder);
        }else{
            viewHolder = (MoviesListItemAdapter.ViewHolder) view.getTag();
        }

        String url = Config.IMAGE_ENDPOINT + movieList.get(i).getPosterPath();
        Picasso.get()
                .load(url)
                .error(R.drawable.image_not_available)
                .into(viewHolder.posterImageView);

        viewHolder.titleTextView.setText(movieList.get(i).getTitle());
        viewHolder.releaseDateTextView.setText(
            String.format(
                this.context.getResources().getString(R.string.release_date),
                movieList.get(i).getReleaseDate()
            )
        );

        return view;
    }

    public static class ViewHolder{
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView releaseDateTextView;
    }
}
