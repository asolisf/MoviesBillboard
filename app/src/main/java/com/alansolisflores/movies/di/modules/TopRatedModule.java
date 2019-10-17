package com.alansolisflores.movies.di.modules;

import com.alansolisflores.movies.contracts.TopRatedContract;
import com.alansolisflores.movies.mocks.repositories.entities.requests.MoviesRequest;
import com.alansolisflores.movies.interactors.TopRatedInteractor;
import com.alansolisflores.movies.presenters.TopRatedPresenter;
import com.alansolisflores.movies.mocks.repositories.MoviesRespository;

import dagger.Module;
import dagger.Provides;

@Module
public class TopRatedModule {
    private final TopRatedContract.View view;

    public TopRatedModule(TopRatedContract.View view){
        this.view = view;
    }

    @Provides
    public TopRatedPresenter ProvidePresenter(TopRatedInteractor interactor){
        return new TopRatedPresenter(this.view,interactor);
    }

    @Provides
    public TopRatedInteractor ProvideInteractor(MoviesRespository moviesRespository, MoviesRequest apiService){
        return new TopRatedInteractor(moviesRespository,apiService);
    }

    @Provides
    public MoviesRespository ProvideRepository(){
        return new MoviesRespository();
    }
}
