package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.entities.objects.Movie;

import java.util.List;

import javax.inject.Inject;

public class PopularPresenter
        implements PopularContract.Presenter, PopularContract.InteractorOutput {

    private PopularContract.View view;

    private final PopularContract.Interactor interactor;

    @Inject
    public PopularPresenter(PopularContract.View view,
                            PopularContract.Interactor interactor){
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
