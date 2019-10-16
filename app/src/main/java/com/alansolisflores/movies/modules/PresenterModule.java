package com.alansolisflores.movies.modules;

import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.contracts.TopRatedContract;
import com.alansolisflores.movies.contracts.UpcomingContract;
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
    abstract PopularContract.InteractorOutput ProvidePopularInteractorOutput(PopularPresenter popularPresenter);

    @Binds
    abstract TopRatedContract.InteractorOutput ProvideTopRatedInteractorOutput(TopRatedPresenter topRatedPresenter);

    @Binds
    abstract UpcomingContract.InteractorOutput ProvideUpcomingInteractorOutput(UpcomingPresenter upcomingPresenter);

    @Binds
    abstract SearchContract.InteractorOutput ProvideSearchInteractorOutput(SearchPresenter searchPresenter);

    @Binds
    abstract DetailContract.InteractorOutput ProvideDetailInteractorOutput(DetailPresenter detailPresenter);

    @Binds
    abstract PopularContract.Presenter ProvidePopularPresenter(PopularPresenter popularPresenter);

    @Binds
    abstract TopRatedContract.Presenter ProvideTopRatedPresenter(TopRatedPresenter topRatedPresenter);

    @Binds
    abstract UpcomingContract.Presenter ProvideUpcomingPresenter(UpcomingPresenter upcomingPresenter);

    @Binds
    abstract SearchContract.Presenter ProvideSearchPresenter(SearchPresenter searchPresenter);

    @Binds
    abstract DetailContract.Presenter ProvideDetailPresenter(DetailPresenter detailPresenter);
}
