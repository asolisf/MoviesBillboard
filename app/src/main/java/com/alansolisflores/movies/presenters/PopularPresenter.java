package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.interactors.PopularInteractor;

public class PopularPresenter
        implements MoviesContract.Presenter, MoviesContract.InteractorOutput {

    private MoviesContract.View view;

    private MoviesContract.Interactor interactor;

    public PopularPresenter(MoviesContract.View view){
        this.view = view;
        this.interactor = new PopularInteractor(this);
    }

    @Override
    public void loadData() {
        this.interactor.getData();
    }

    @Override
    public void onGetDataSuccess(MoviesResponse moviesResponse) {
        this.view.getData(moviesResponse.getResults());
    }

    @Override
    public void onGetDataError(String message) {
        this.view.showMessage(message);
    }
}
