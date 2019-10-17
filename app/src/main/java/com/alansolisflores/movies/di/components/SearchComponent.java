package com.alansolisflores.movies.di.components;

import com.alansolisflores.movies.di.PerActivity;
import com.alansolisflores.movies.di.modules.SearchModule;
import com.alansolisflores.movies.views.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

@PerActivity
@Component(modules = SearchModule.class,dependencies = ApplicationComponent.class)
public interface SearchComponent {
    void Inject(SearchActivity searchActivity);
}
