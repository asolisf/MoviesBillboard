package com.alansolisflores.movies.di.modules;

import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.interactors.PopularInteractor;
import com.alansolisflores.movies.presenters.PopularPresenter;
import com.alansolisflores.movies.repositories.MoviesRespository;

import dagger.Module;
import dagger.Provides;

@Module
public class PopularModule {

    private final PopularContract.View view;

    public PopularModule(PopularContract.View view){
        this.view = view;
    }

    @Provides
    public PopularPresenter ProvidePresenter(PopularInteractor interactor){
        return new PopularPresenter(this.view,interactor);
    }

    @Provides
    public PopularInteractor ProvideInteractor(MoviesRespository moviesRespository, MoviesRequest apiService){
        return new PopularInteractor(moviesRespository,apiService);
    }

    @Provides
    public MoviesRespository ProvideRepository(){
        return new MoviesRespository();
    }
}
