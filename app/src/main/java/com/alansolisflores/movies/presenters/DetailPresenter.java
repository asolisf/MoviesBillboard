package com.alansolisflores.movies.presenters;

import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.entities.objects.Video;
import com.alansolisflores.movies.interactors.DetailInteractor;

import java.util.List;

public class DetailPresenter implements  DetailContract.Presenter,DetailContract.InteractorOutput {

    private DetailContract.View view;

    private DetailContract.Interactor interactor;

    public DetailPresenter(DetailContract.View view){

        this.view = view;
        this.interactor = new DetailInteractor(this);
    }

    @Override
    public void onGetDataSuccess(List<Video> videoList) {
        this.view.GetData(videoList);
    }

    @Override
    public void onGetDataError(String message) {
        this.view.ShowMessage(message);
    }

    @Override
    public void LoadData(int id) {
        this.interactor.GetData(id);
    }
}
