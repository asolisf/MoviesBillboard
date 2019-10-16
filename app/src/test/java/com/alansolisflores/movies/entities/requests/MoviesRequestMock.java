package com.alansolisflores.movies.entities.requests;

import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.entities.responses.VideosResponse;

import retrofit2.Call;

public class MoviesRequestMock implements MoviesRequest {
    @Override
    public Call<MoviesResponse> getPopular(String apiKey) {
        return null;
    }

    @Override
    public Call<MoviesResponse> getTopRated(String apiKey) {
        return null;
    }

    @Override
    public Call<MoviesResponse> getUpcoming(String apiKey) {
        return null;
    }

    @Override
    public Call<VideosResponse> getVideos(int id, String apiKey) {
        return null;
    }

    @Override
    public Call<MoviesResponse> getSearchMovies(String title, String apiKey) {
        return null;
    }
}
