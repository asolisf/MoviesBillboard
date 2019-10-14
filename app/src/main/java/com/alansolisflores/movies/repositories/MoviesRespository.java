package com.alansolisflores.movies.repositories;

import com.alansolisflores.movies.App;
import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.objects.MovieCache;

import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MoviesRespository implements MoviesContract.Respository, SearchContract.Repository {

    private Realm realm;

    public MoviesRespository(){
        Realm.init(App.getAppContext());

        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public void SaveDataBySection(List<Movie> movieList,Section section, Date updated) {

        this.deleteDataBySection(section);

        this.realm.beginTransaction();
        MovieCache movieCache = new MovieCache(section,updated);
        for (Movie movie: movieList) {
            movieCache.getMovieList().add(movie);
        }
        this.realm.copyToRealm(movieCache);
        this.realm.commitTransaction();
    }

    private void deleteDataBySection(Section section){
        this.realm.beginTransaction();
        RealmResults<MovieCache> results =
                this.realm.where(MovieCache.class)
                          .equalTo("section",section.toString())
                          .findAll();

        results.deleteAllFromRealm();
        this.realm.commitTransaction();
    }

    @Override
    public List<Movie> GetDataBySection(Section section) {

        MovieCache result =
                this.realm.where(MovieCache.class)
                          .equalTo("section",section.toString())
                          .findAll()
                          .first();


        return result.getMovieList();
    }

    @Override
    public void Dispose() {
        this.realm.close();
    }

    @Override
    public List<Movie> GetDataByTitle(String title) {
        RealmResults<Movie> result =
                this.realm.where(Movie.class)
                        .equalTo("title",title)
                        .findAll();
        return this.realm.copyFromRealm(result);
    }
}
