package com.alansolisflores.movies.repositories;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;

import java.util.Date;
import java.util.List;

public class MoviesRepositoryMock implements MoviesContract.Repository, SearchContract.Repository {
    @Override
    public void SaveDataBySection(List<Movie> movieList, Section section, Date updated) {

    }

    @Override
    public List<Movie> GetDataBySection(Section section) {
        return null;
    }

    @Override
    public List<Movie> GetDataByTitle(String title) {
        return null;
    }

    @Override
    public void Dispose() {

    }
}
