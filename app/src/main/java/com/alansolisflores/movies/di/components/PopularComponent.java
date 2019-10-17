package com.alansolisflores.movies.di.components;

import com.alansolisflores.movies.di.PerActivity;
import com.alansolisflores.movies.di.modules.PopularModule;
import com.alansolisflores.movies.fragments.PopularFragment;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(modules = PopularModule.class,dependencies = ApplicationComponent.class)
public interface PopularComponent {

    void Inject(PopularFragment popularFragment);
}
