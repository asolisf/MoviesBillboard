package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.responses.MoviesResponse;

public interface MainContract {

    interface View{
        void showMessage(String message);
    }

    interface Presenter{

    }

    interface Interactor{
        void getTopRanked();
    }

    interface InteractorOutput{
        void onGetTopRankedSuccess(MoviesResponse moviesResponse);
        void onGetTopRankedError(String message);
    }
}
