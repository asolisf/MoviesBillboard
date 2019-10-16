package com.alansolisflores.movies.modules;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.repositories.MoviesRespository;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesRepositoryModule {

    @Provides
    public MoviesContract.Repository provideMoviesRepository(){
        return new MoviesRespository();
    }

    @Provides
    public SearchContract.Repository provideSearchRepository(){
        return new MoviesRespository();
    }
}