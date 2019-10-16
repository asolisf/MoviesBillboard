package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.objects.Video;

import java.util.List;

public interface DetailContract {
    interface View{
        void ShowError(String Message);
        void GetData(List<Video> videoList);
    }

    interface Presenter{
        void LoadData(int id);
    }

    interface Interactor{
        void GetData(int id);
    }

    interface InteractorOutput{
        void onGetDataSuccess(List<Video> videoList);
        void onGetDataError(String message);
    }
}
