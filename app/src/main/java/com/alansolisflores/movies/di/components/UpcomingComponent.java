package com.alansolisflores.movies.di.components;

import com.alansolisflores.movies.di.PerActivity;
import com.alansolisflores.movies.di.modules.UpcomingModule;
import com.alansolisflores.movies.fragments.UpcomingFragment;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(modules = UpcomingModule.class,dependencies = ApplicationComponent.class)
public interface UpcomingComponent {
    void Inject(UpcomingFragment upcomingFragment);
}
