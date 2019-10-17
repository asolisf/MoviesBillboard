package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.mocks.repositories.entities.objects.Movie;

import java.util.List;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter,SearchContract.InteractorOutput {

    private final SearchContract.Interactor interactor;

    private final SearchContract.View view;

    @Inject
    public SearchPresenter(SearchContract.View view,
                           SearchContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void SearchData(String title) {
        this.interactor.LoadDataByTitle(title);
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
