package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.interactors.SearchInteractor;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter,SearchContract.InteractorOutput {

    private SearchContract.Interactor interactor;

    private SearchContract.View view;

    public SearchPresenter(SearchContract.View view){
        this.view = view;
        this.interactor = new SearchInteractor(this);
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
