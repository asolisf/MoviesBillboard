package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.responses.MoviesResponse;

import java.util.List;

public interface MainContract {

    interface View{
        void showMessage(String message);
        void setPopular(List<Movie> movieList);
    }

    interface Presenter{
        void loadPopular();
    }

    interface Interactor{
        void getPopular();
    }

    interface InteractorOutput{
        void onGetPopularSuccess(MoviesResponse moviesResponse);
        void onGetPopularError(String message);
    }

    interface Router{
        void goToMovieDetail(int id);
        void goToSeachScreen();
    }

    interface MovieFragment{
        void getData(List<Movie> movieList);
    }
}
