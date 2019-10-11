package com.alansolisflores.movies.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.alansolisflores.movies.R;
import com.alansolisflores.movies.adapters.MoviePreviewAdapter;
import com.alansolisflores.movies.contracts.MainContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.presenters.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class PopularFragment extends Fragment implements MainContract.View{

    private View view;

    private GridView gridView;

    private MoviePreviewAdapter moviePreviewAdapter;

    private List<Movie> movieList;

    private MainPresenter presenter;

    public PopularFragment() {
        this.movieList = new ArrayList<Movie>();
        this.presenter = new MainPresenter(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_popular, container, false);
        this.gridView = view.findViewById(R.id.moviesGridView);

        return this.view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.presenter.loadPopular();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }

    @Override
    public void setPopular(List<Movie> movieList) {
        this.moviePreviewAdapter = new MoviePreviewAdapter(movieList,getContext(),R.layout.movie_preview_item);
        this.gridView.setAdapter(this.moviePreviewAdapter);
    }
}
