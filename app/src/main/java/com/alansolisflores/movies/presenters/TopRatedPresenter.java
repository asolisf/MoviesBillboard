package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.TopRatedContract;
import com.alansolisflores.movies.entities.objects.Movie;

import java.util.List;

import javax.inject.Inject;

public class TopRatedPresenter implements TopRatedContract.Presenter,
        TopRatedContract.InteractorOutput{

    private final TopRatedContract.View view;

    private final TopRatedContract.Interactor interactor;

    @Inject
    public TopRatedPresenter(TopRatedContract.View view,
                             TopRatedContract.Interactor interactor){
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
