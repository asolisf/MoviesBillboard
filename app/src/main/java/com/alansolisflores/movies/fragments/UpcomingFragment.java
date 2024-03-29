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
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alansolisflores.movies.App;
import com.alansolisflores.movies.R;
import com.alansolisflores.movies.adapters.MoviePreviewAdapter;
import com.alansolisflores.movies.contracts.UpcomingContract;
import com.alansolisflores.movies.di.components.DaggerUpcomingComponent;
import com.alansolisflores.movies.di.components.UpcomingComponent;
import com.alansolisflores.movies.di.modules.UpcomingModule;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.presenters.UpcomingPresenter;
import com.alansolisflores.movies.views.MovieDetailActivity;
import com.alansolisflores.movies.views.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UpcomingFragment extends Fragment implements UpcomingContract.View,
Toolbar.OnMenuItemClickListener, AdapterView.OnItemClickListener{

    private View view;

    private GridView gridView;

    private MoviePreviewAdapter moviePreviewAdapter;

    private List<Movie> movieList;

    @Inject
    UpcomingPresenter presenter;

    private RelativeLayout loadingDataLayout;

    private Toolbar customToolbar;

    private RelativeLayout errorLayout;


    public UpcomingFragment(){
        this.movieList = new ArrayList<Movie>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_popular, container, false);
        this.initializeProperties();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(this.movieList.size() == 0)
        {
            this.presenter.LoadData();
        }
    }

    @Override
    public void ShowError(String message) {
        this.errorLayout.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void GetData(List<Movie> movieList) {
        this.movieList = movieList;
        this.loadingDataLayout.setVisibility(View.INVISIBLE);
        this.moviePreviewAdapter
                = new MoviePreviewAdapter(this.movieList, getContext(), R.layout.movie_preview_item);
        this.gridView.setAdapter(this.moviePreviewAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.searchItem:
                this.goToSearchScreen();
                return true;
            default:
                return false;
        }
    }

    private void goToSearchScreen(){
        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.goToDetailScreen(this.movieList.get(i));
    }

    private void goToDetailScreen(Movie movie){
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra("id",movie.getId());
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("date",movie.getReleaseDate());
        intent.putExtra("image",movie.getPosterPath());
        intent.putExtra("overview",movie.getOverview());
        intent.putExtra("voteAverage",movie.getVoteAverage());
        startActivity(intent);
    }

    private void initializeProperties(){

        UpcomingComponent upcomingComponent =
                DaggerUpcomingComponent.builder()
                        .applicationComponent(App.get(getContext()).component())
                        .upcomingModule(new UpcomingModule(this)).build();
        upcomingComponent.Inject(this);

        this.gridView = view.findViewById(R.id.moviesGridView);
        this.loadingDataLayout = view.findViewById(R.id.loadingDataLayout);
        this.customToolbar = view.findViewById(R.id.toolbar);
        this.errorLayout = view.findViewById(R.id.errorLayout);
        this.customToolbar.setTitle(R.string.upcoming);
        this.customToolbar.inflateMenu(R.menu.toolbar_menu);
        this.customToolbar.setOnMenuItemClickListener(this);
        this.gridView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        this.presenter.OnDestroy();
        super.onDestroy();
    }
}
