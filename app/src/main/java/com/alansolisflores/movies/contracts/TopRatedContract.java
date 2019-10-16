package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;

import java.util.Date;
import java.util.List;

public interface TopRatedContract {
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
        void SaveDataBySection(List<Movie> movieList, Section section, Date updated);
        List<Movie> GetDataBySection(Section section);
        void Dispose();
    }
}
