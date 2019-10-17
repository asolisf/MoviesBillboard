package com.alansolisflores.movies.di.modules;

import android.app.Application;
import android.content.Context;

import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.helpers.Config;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Context ProvideContext(){
        return this.application;
    }

    @Provides
    @Singleton
    public Application ProvideApplication(){
        return this.application;
    }

    @Provides
    @Singleton
    public Retrofit ProvideRetrofit(){
        return new Retrofit.Builder().baseUrl(Config.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    @Provides
    @Singleton
    public MoviesRequest ProvideApiService(Retrofit retrofit){
        return retrofit.create(MoviesRequest.class);
    }

    @Provides
    @Singleton
    public Realm ProvideRealm(){
        return Realm.getDefaultInstance();
    }
}
