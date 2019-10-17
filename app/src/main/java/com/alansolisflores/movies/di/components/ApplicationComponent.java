package com.alansolisflores.movies.di.components;

import android.app.Application;
import android.content.Context;

import com.alansolisflores.movies.di.modules.ApplicationModule;
import com.alansolisflores.movies.entities.requests.MoviesRequest;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {

    Context GetContext();
    Application GetApplication();
    Retrofit GetRetrofit();
    MoviesRequest GetApiService();
    Realm GetRealm();
}
