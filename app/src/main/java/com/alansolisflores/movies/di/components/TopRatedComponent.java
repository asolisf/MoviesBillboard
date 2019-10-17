package com.alansolisflores.movies.di.components;

import com.alansolisflores.movies.di.PerActivity;
import com.alansolisflores.movies.di.modules.TopRatedModule;
import com.alansolisflores.movies.fragments.TopRatedFragment;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(modules = TopRatedModule.class,dependencies = ApplicationComponent.class)
public interface TopRatedComponent {
    void Inject(TopRatedFragment topRatedFragment);
}
