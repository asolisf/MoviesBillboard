package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.mocks.repositories.entities.enums.Section;
import com.alansolisflores.movies.mocks.repositories.entities.objects.Movie;

import java.util.Date;
import java.util.List;

public interface PopularContract {

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
        void Subscribe(PopularContract.InteractorOutput interactorOutput);
        void Unsubscribe();
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
