package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.interactors.PopularInteractor;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.Date;
import java.util.List;

public class PopularPresenter
        implements MoviesContract.Presenter, MoviesContract.InteractorOutput {

    private MoviesContract.View view;

    private MoviesContract.Interactor interactor;

    private MoviesContract.Respository repository;

    public PopularPresenter(MoviesContract.View view){
        this.view = view;
        this.interactor = new PopularInteractor(this);
        this.repository = new MoviesRespository();
    }

    @Override
    public void loadData() {
        this.interactor.getData();
    }

    @Override
    public void onDestroy() {
        this.repository.Dispose();
    }

    @Override
    public void onGetDataSuccess(List<Movie> movieList) {
        this.view.getData(movieList);
    }

    @Override
    public void onGetDataError(String message) {
        this.view.showMessage(message);
    }
}
