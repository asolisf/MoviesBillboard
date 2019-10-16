package com.alansolisflores.movies.repositories;

import com.alansolisflores.movies.contracts.PopularContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.contracts.TopRatedContract;
import com.alansolisflores.movies.contracts.UpcomingContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.objects.MovieCache;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

public class MoviesRespository implements PopularContract.Repository,
        TopRatedContract.Repository,
        UpcomingContract.Repository,
        SearchContract.Repository {

    private Realm realm;

    public MoviesRespository(){}

    @Override
    public void SaveDataBySection(List<Movie> movieList,Section section, Date updated) {

        this.deleteDataBySection(section);

        this.getRealmInstance().beginTransaction();
        MovieCache movieCache = new MovieCache(section,updated);
        for (Movie movie: movieList) {
            movieCache.getMovieList().add(movie);
        }
        this.getRealmInstance().copyToRealm(movieCache);
        this.getRealmInstance().commitTransaction();
    }

    private void deleteDataBySection(Section section){
        this.getRealmInstance().beginTransaction();
        RealmResults<MovieCache> results =
                this.getRealmInstance().where(MovieCache.class)
                          .equalTo("section",section.toString())
                          .findAll();

        results.deleteAllFromRealm();
        this.getRealmInstance().commitTransaction();
    }

    @Override
    public List<Movie> GetDataBySection(Section section) {

        MovieCache result =
                this.getRealmInstance().where(MovieCache.class)
                          .equalTo("section",section.toString())
                          .findAll()
                          .first();


        return result.getMovieList();
    }

    @Override
    public void Dispose() {
        this.getRealmInstance().close();
    }

    @Override
    public List<Movie> GetDataByTitle(String title) {
        RealmResults<Movie> result =
                this.getRealmInstance().where(Movie.class)
                        .equalTo("title",title)
                        .findAll();
        return this.getRealmInstance().copyFromRealm(result);
    }

    private Realm getRealmInstance(){
        if(this.realm == null){
            this.realm = Realm.getDefaultInstance();
        }

        return this.realm;
    }
}
