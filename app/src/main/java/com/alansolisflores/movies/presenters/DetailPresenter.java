package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.entities.objects.Video;

import java.util.List;

import javax.inject.Inject;

public class DetailPresenter implements  DetailContract.Presenter,DetailContract.InteractorOutput {

    private DetailContract.View view;

    private final DetailContract.Interactor interactor;

    @Inject
    public DetailPresenter(DetailContract.View view,
                          DetailContract.Interactor interactor){
        this.view = view;
        this.interactor = interactor;
        this.interactor.Subscribe(this);
    }

    @Override
    public void onGetDataSuccess(List<Video> videoList) {
        this.view.GetData(videoList);
    }

    @Override
    public void onGetDataError(String message) {
        this.view.ShowError(message);
    }

    @Override
    public void LoadData(int id) {
        this.interactor.GetData(id);
    }
}
