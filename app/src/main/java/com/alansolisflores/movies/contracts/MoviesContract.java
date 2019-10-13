package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.responses.MoviesResponse;

import java.util.List;

public interface MoviesContract {

    interface View{
        void showMessage(String message);
        void getData(List<Movie> movieList);
    }

    interface Presenter{
        void loadData();
    }

    interface Interactor{
        void getData();
    }

    interface InteractorOutput{
        void onGetDataSuccess(MoviesResponse moviesResponse);
        void onGetDataError(String message);
    }

    interface Respository{
        void saveData(List<Movie> movieList);
    }
}
