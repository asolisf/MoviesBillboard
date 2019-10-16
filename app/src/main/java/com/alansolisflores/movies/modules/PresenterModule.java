package com.alansolisflores.movies.modules;

import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.presenters.DetailPresenter;
import com.alansolisflores.movies.presenters.PopularPresenter;
import com.alansolisflores.movies.presenters.SearchPresenter;
import com.alansolisflores.movies.presenters.TopRatedPresenter;
import com.alansolisflores.movies.presenters.UpcomingPresenter;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PresenterModule {

    @Binds
    @Named("PopularPresenter")
    abstract MoviesContract.Presenter providesPopularPresenter(PopularPresenter popularPresenter);

    @Binds
    @Named("UpcomingPresenter")
    abstract MoviesContract.Presenter providesUpcomingPresenter(UpcomingPresenter upcomingPresenter);

    @Binds
    @Named("TopRatedPresenter")
    abstract MoviesContract.Presenter providesTopRatedPresenter(TopRatedPresenter topRatedPresenter);

    @Binds
    @Named("DetailPresenter")
    abstract DetailContract.Presenter providesDetailPresenter(DetailPresenter detailPresenter);

    @Binds
    @Named("SearchPresenter")
    abstract SearchContract.Presenter searchPresenter(SearchPresenter searchPresenter);

    @Binds
    @Named("InteractorOutputPresenter")
    abstract MoviesContract.InteractorOutput providesTopRatedInteractorOutput(TopRatedPresenter topRatedPresenter);

}
