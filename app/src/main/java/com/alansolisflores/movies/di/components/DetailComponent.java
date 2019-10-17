package com.alansolisflores.movies.di.components;

import com.alansolisflores.movies.di.PerActivity;
import com.alansolisflores.movies.di.modules.DetailModule;
import com.alansolisflores.movies.views.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(modules = DetailModule.class,dependencies = ApplicationComponent.class)
public interface DetailComponent {
    void Inject(MovieDetailActivity movieDetailActivity);
}
