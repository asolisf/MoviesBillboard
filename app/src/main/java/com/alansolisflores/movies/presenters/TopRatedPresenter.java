package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.interactors.TopRatedInteractor;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.List;

public class TopRatedPresenter implements MoviesContract.Presenter,
        MoviesContract.InteractorOutput{

    private MoviesContract.View view;

    private MoviesContract.Interactor interactor;

    private MoviesContract.Respository repository;

    public TopRatedPresenter(MoviesContract.View view){
        this.view = view;
        this.interactor = new TopRatedInteractor(this);
        this.repository = new MoviesRespository();
    }

    @Override
    public void LoadData() {
        this.interactor.GetData();
    }

    @Override
    public void OnDestroy() {
        this.repository.Dispose();
    }

    @Override
    public void OnGetDataSuccess(List<Movie> movieList) {
        this.view.GetData(movieList);
    }

    @Override
    public void onGetDataError(String message) {
        this.view.ShowMessage(message);
    }
}
