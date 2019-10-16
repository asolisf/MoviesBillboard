package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.interactors.PopularInteractor;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.List;

import javax.inject.Inject;

public class PopularPresenter
        implements MoviesContract.Presenter, MoviesContract.InteractorOutput {

    private MoviesContract.View view;

    private final MoviesContract.Interactor interactor;

    @Inject
    public PopularPresenter(MoviesContract.View view,
                            MoviesContract.Interactor interactor){
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
