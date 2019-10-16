package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.interactors.TopRatedInteractor;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.List;

import javax.inject.Inject;

public class TopRatedPresenter implements MoviesContract.Presenter,
        MoviesContract.InteractorOutput{

    private final MoviesContract.View view;

    private final MoviesContract.Interactor interactor;

    @Inject
    public TopRatedPresenter(MoviesContract.View view,MoviesContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void LoadData() {
        this.interactor.GetData();
    }

    @Override
    public void OnDestroy() {
        this.interactor.Dispose();
    }

    @Override
    public void OnGetDataSuccess(List<Movie> movieList) {
        this.view.GetData(movieList);
    }

    @Override
    public void OnGetDataError(String message) {
        this.view.ShowError(message);
    }
}
