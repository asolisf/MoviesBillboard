package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.objects.MovieCache;
import com.alansolisflores.movies.entities.responses.MoviesResponse;

import java.util.Date;
import java.util.List;

import dagger.Provides;

public interface MoviesContract {

    interface View{
        void ShowError(String message);
        void GetData(List<Movie> movieList);
    }

    interface Presenter{
        void LoadData();
        void OnDestroy();
    }

    interface Interactor{
        void GetData();
        void Dispose();
    }

    interface InteractorOutput{
        void OnGetDataSuccess(List<Movie> movieList);
        void OnGetDataError(String message);
    }

    interface Repository {
        void SaveDataBySection(List<Movie> movieList,Section section, Date updated);
        List<Movie> GetDataBySection(Section section);
        void Dispose();
    }
}
