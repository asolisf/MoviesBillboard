package com.alansolisflores.movies.modules;

import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.helpers.Config;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {

    @Provides
    public MoviesRequest getApiService(){
        return provideRetrofit(Config.ENDPOINT).create(MoviesRequest.class);
    }

    private Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
}
