package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.objects.Movie;

import java.util.List;

public interface SearchContract {

    interface View{
        void ShowError(String message);
        void GetData(List<Movie> movieList);
    }

    interface Presenter{
        void SearchData(String title);
        void OnDestroy();
    }

    interface Interactor{
        void LoadDataByTitle(String title);
        void Dispose();
    }

    interface  InteractorOutput{
        void OnGetDataSuccess(List<Movie> movieList);
        void OnGetDataError(String message);
    }

    interface Repository{
        List<Movie> GetDataByTitle(String title);
        void Dispose();
    }
}
