package com.alansolisflores.movies.entities.requests;

import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.entities.responses.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesRequest {

    @GET("movie/popular")
    Call<MoviesResponse> getPopular(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRated(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcoming(@Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Call<VideosResponse> getVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MoviesResponse> getSearchMovies(@Query("query") String title, @Query("api_key") String apiKey);
}
