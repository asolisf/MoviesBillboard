package com.alansolisflores.movies.components;

import com.alansolisflores.movies.fragments.PopularFragment;
import com.alansolisflores.movies.fragments.TopRatedFragment;
import com.alansolisflores.movies.fragments.UpcomingFragment;
import com.alansolisflores.movies.modules.PresenterModule;
import com.alansolisflores.movies.views.MovieDetailActivity;
import com.alansolisflores.movies.views.SearchActivity;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = PresenterModule.class)
public interface PresenterComponent {
    void Inject(PopularFragment popularFragment);
    void Inject(TopRatedFragment topRatedFragment);
    void Inject(UpcomingFragment upcomingFragment);
    void Inject(MovieDetailActivity movieDetailActivity);
    void Inject(SearchActivity searchActivity);

}
