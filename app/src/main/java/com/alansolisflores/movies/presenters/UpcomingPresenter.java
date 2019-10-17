package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.UpcomingContract;
import com.alansolisflores.movies.entities.objects.Movie;

import java.util.List;

import javax.inject.Inject;

public class UpcomingPresenter
        implements UpcomingContract.Presenter,
        UpcomingContract.InteractorOutput {

    private final UpcomingContract.View view;

    private final UpcomingContract.Interactor interactor;

    @Inject
    public UpcomingPresenter(UpcomingContract.View view,
                             UpcomingContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
        this.interactor.Subscribe(this);
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
