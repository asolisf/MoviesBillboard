package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.MainContract;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.interactors.MoviesInteractor;

public class MainPresenter implements MainContract.Presenter, MainContract.InteractorOutput {

    private MainContract.View view;

    private MainContract.Interactor interactor;

    public MainPresenter(MainContract.View view){
        this.view = view;
        this.interactor = new MoviesInteractor(this);
    }

    @Override
    public void onGetTopRankedSuccess(MoviesResponse moviesResponse) {

    }

    @Override
    public void onGetTopRankedError(String message) {
        view.showMessage(message);
    }
}