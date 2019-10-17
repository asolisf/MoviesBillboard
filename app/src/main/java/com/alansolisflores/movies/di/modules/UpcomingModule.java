package com.alansolisflores.movies.di.modules;

import com.alansolisflores.movies.contracts.UpcomingContract;
import com.alansolisflores.movies.mocks.repositories.entities.requests.MoviesRequest;
import com.alansolisflores.movies.interactors.UpcomingInteractor;
import com.alansolisflores.movies.presenters.UpcomingPresenter;
import com.alansolisflores.movies.mocks.repositories.MoviesRespository;

import dagger.Module;
import dagger.Provides;

@Module
public class UpcomingModule {

    private final UpcomingContract.View view;

    public UpcomingModule(UpcomingContract.View view){
        this.view = view;
    }

    @Provides
    public UpcomingPresenter ProvidePresenter(UpcomingInteractor interactor){
        return new UpcomingPresenter(this.view,interactor);
    }

    @Provides
    public UpcomingInteractor ProvideInteractor(MoviesRespository moviesRespository, MoviesRequest apiService){
        return new UpcomingInteractor(moviesRespository,apiService);
    }

    @Provides
    public MoviesRespository ProvideRepository(){
        return new MoviesRespository();
    }
}
