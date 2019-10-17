package com.alansolisflores.movies.di.modules;

import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.interactors.DetailInteractor;
import com.alansolisflores.movies.presenters.DetailPresenter;
import com.alansolisflores.movies.repositories.MoviesRespository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    private final DetailContract.View view;

    public DetailModule(DetailContract.View view){
        this.view = view;
    }

    @Provides
    public DetailPresenter ProvidePresenter(DetailInteractor interactor){
        return new DetailPresenter(this.view,interactor);
    }

    @Provides
    public DetailInteractor ProvideInteractor(MoviesRequest apiService){
        return new DetailInteractor(apiService);
    }

    @Provides
    public MoviesRespository ProvideRepository(){
        return new MoviesRespository();
    }
}
