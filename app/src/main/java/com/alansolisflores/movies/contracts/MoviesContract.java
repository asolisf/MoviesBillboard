package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.objects.MovieCache;
import com.alansolisflores.movies.entities.responses.MoviesResponse;

import java.util.Date;
import java.util.List;

public interface MoviesContract {

    interface View{
        void showMessage(String message);
        void getData(List<Movie> movieList);
    }

    interface Presenter{
        void loadData();
        void onDestroy();
    }

    interface Interactor{
        void getData();
        void Dispose();
    }

    interface InteractorOutput{
        void onGetDataSuccess(List<Movie> movieList);
        void onGetDataError(String message);
    }

    interface Respository{
        void SaveDataBySection(List<Movie> movieList,Section section, Date updated);
        List<Movie> GetDataBySection(Section section);
        void Dispose();
    }
}
