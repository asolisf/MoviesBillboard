package com.alansolisflores.movies.interactors;


import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.helpers.Config;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor implements SearchContract.Interactor, Callback<MoviesResponse> {

    private SearchContract.InteractorOutput interactorOutput;

    private String title;

    private final SearchContract.Repository repository;

    private final MoviesRequest moviesRequest;

    @Inject
    public SearchInteractor(SearchContract.Repository repository,
                            MoviesRequest moviesRequest){
        this.repository = repository;
        this.moviesRequest = moviesRequest;
    }

    @Override
    public void LoadDataByTitle(String title) {
        this.title = title;
        Call<MoviesResponse> call = moviesRequest.getSearchMovies(title, Config.API_KEY);

        call.enqueue(this);
    }

    @Override
    public void Dispose() {
        this.repository.Dispose();
    }

    @Override
    public void Subscribe(SearchContract.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void Unsubscribe() {
        this.interactorOutput = null;
    }

    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        if(response.isSuccessful()){
            this.interactorOutput.OnGetDataSuccess(response.body().getResults());
        }else {
            List<Movie> movieList = this.loadDataFromCache();
            if(movieList.size() == 0){
                this.interactorOutput.OnGetDataError(response.message());
            }else {
                this.interactorOutput.OnGetDataSuccess(movieList);
            }
        }
    }

    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {
        List<Movie> movieList = this.loadDataFromCache();
        if(movieList.size() == 0){
            this.interactorOutput.OnGetDataError(Config.NETWORK_ERROR);
        }else {
            this.interactorOutput.OnGetDataSuccess(movieList);
        }
    }

    private List<Movie> loadDataFromCache(){
        return this.repository.GetDataByTitle(title);
    }
}
