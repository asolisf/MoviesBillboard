package com.alansolisflores.movies.di.modules;

import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.interactors.SearchInteractor;
import com.alansolisflores.movies.presenters.SearchPresenter;
import com.alansolisflores.movies.repositories.MoviesRespository;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private final SearchContract.View view;

    public SearchModule(SearchContract.View view){
        this.view = view;
    }

    @Provides
    public SearchPresenter ProvidePresenter(SearchInteractor interactor){
        return new SearchPresenter(this.view,interactor);
    }

    @Provides
    public SearchInteractor ProvideInteractor(MoviesRespository moviesRespository, MoviesRequest apiService){
        return new SearchInteractor(moviesRespository,apiService);
    }

    @Provides
    public MoviesRespository ProvideRepository(){
        return new MoviesRespository();
    }
}
