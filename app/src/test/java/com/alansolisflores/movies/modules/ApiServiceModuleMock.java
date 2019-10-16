package com.alansolisflores.movies.modules;

import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.requests.MoviesRequestMock;

import dagger.Module;

@Module
public class ApiServiceModuleMock extends ApiServiceModule{

    @Override
    public MoviesRequest getApiService(){
        return new MoviesRequestMock();
    }
}
