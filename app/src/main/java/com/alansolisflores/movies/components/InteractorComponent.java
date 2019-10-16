package com.alansolisflores.movies.components;

import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.interactors.DetailInteractor;
import com.alansolisflores.movies.interactors.PopularInteractor;
import com.alansolisflores.movies.interactors.SearchInteractor;
import com.alansolisflores.movies.interactors.TopRatedInteractor;
import com.alansolisflores.movies.interactors.UpcomingInteractor;
import com.alansolisflores.movies.modules.ApiServiceModule;
import com.alansolisflores.movies.modules.MoviesRepositoryModule;
import com.alansolisflores.movies.modules.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {MoviesRepositoryModule.class, ApiServiceModule.class, PresenterModule.class})
public interface InteractorComponent {

    PopularContract.Repository moviesRepository();

    SearchContract.Repository searchRepository();

    void Inject(PopularInteractor interactor);
    void Inject(DetailInteractor interactor);
    void Inject(SearchInteractor interactor);
    void Inject(TopRatedInteractor interactor);
    void Inject(UpcomingInteractor interactor);
}
