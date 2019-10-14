package com.alansolisflores.movies.contracts;

import com.alansolisflores.movies.entities.objects.Movie;

import java.util.List;

public interface SearchContract {

    interface View{

    }

    interface Presenter{

    }

    interface Repository{
        List<Movie> getDataByTitle(String title);
    }
}
