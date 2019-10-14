package com.alansolisflores.movies.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alansolisflores.movies.R;
import com.alansolisflores.movies.adapters.MoviesListItemAdapter;
import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.presenters.TopRatedPresenter;
import com.alansolisflores.movies.views.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class TopRatedFragment extends Fragment implements MoviesContract.View,
        Toolbar.OnMenuItemClickListener, AdapterView.OnItemClickListener {

    private View view;

    private ListView listView;

    private MoviesListItemAdapter movieListItemAdapter;

    private List<Movie> movieList;

    private TopRatedPresenter presenter;

    private RelativeLayout loadingDataLayout;

    private Toolbar customToolbar;

    public TopRatedFragment() {
        this.movieList = new ArrayList<Movie>();
        this.presenter = new TopRatedPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_top_rated, container, false);
        this.initializeProperties();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (this.movieList.size() == 0) {
            this.presenter.loadData();
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getData(List<Movie> movieList) {
        this.movieList = movieList;
        this.loadingDataLayout.setVisibility(View.INVISIBLE);
        this.movieListItemAdapter
                = new MoviesListItemAdapter(this.movieList, getContext(), R.layout.movie_list_item);
        this.listView.setAdapter(this.movieListItemAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.goToDetailScreen(this.movieList.get(i));
    }

    private void goToDetailScreen(Movie movie) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("title", movie.getTitle());
        intent.putExtra("date", movie.getReleaseDate());
        intent.putExtra("image", movie.getPosterPath());
        intent.putExtra("overview", movie.getOverview());
        intent.putExtra("voteAverage", movie.getVoteAverage());
        startActivity(intent);
    }

    private void initializeProperties() {
        this.listView = view.findViewById(R.id.moviesListView);
        this.loadingDataLayout = view.findViewById(R.id.loadingDataLayout);
        this.customToolbar = view.findViewById(R.id.toolbar);
        this.customToolbar.setTitle(R.string.top_rated);
        this.customToolbar.inflateMenu(R.menu.toolbar_menu);
        this.customToolbar.setOnMenuItemClickListener(this);
        this.listView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }
}
