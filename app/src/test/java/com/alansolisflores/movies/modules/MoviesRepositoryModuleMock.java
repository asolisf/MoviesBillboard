package com.alansolisflores.movies.modules;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.repositories.MoviesRepositoryMock;

public class MoviesRepositoryModuleMock extends MoviesRepositoryModule {
    @Override
    public MoviesContract.Repository provideMoviesRepository() {
        return new MoviesRepositoryMock();
    }

    @Override
    public SearchContract.Repository provideSearchRepository() {
        return new MoviesRepositoryMock();
    }
}
