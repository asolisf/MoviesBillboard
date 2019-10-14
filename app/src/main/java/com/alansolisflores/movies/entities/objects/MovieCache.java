package com.alansolisflores.movies.entities.objects;

import com.alansolisflores.movies.entities.enums.Section;

import java.util.Date;
import io.realm.RealmList;
import io.realm.RealmObject;

public class MovieCache extends RealmObject {

    private String section;

    private RealmList<Movie> movieList;

    private Date updated;

    public MovieCache(){
        //Realm required
    }

    public MovieCache(Section section,  Date updated) {
        this.section = section.toString();
        this.movieList = new RealmList<Movie>();
        this.updated = updated;
    }

    public Section getSection() {
        return Section.valueOf(this.section);
    }

    public void setSection(Section section) {
        this.section = section.toString();
    }

    public RealmList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(RealmList<Movie> movieList) {
        this.movieList = movieList;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
