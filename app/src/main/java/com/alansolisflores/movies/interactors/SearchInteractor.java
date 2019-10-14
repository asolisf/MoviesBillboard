package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.App;
import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.helpers.Config;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchInteractor implements SearchContract.Interactor, Callback<MoviesResponse> {

    private SearchContract.InteractorOutput interactorOutput;

    private SearchContract.Repository repository;

    private String title;

    public SearchInteractor(SearchContract.InteractorOutput interactorOutput){
        this.interactorOutput = interactorOutput;
        this.repository = new MoviesRespository();
    }

    @Override
    public void LoadDataByTitle(String title) {
        this.title = title;
        Call<MoviesResponse> call = App.getApiService().getSearchMovies(title, Config.API_KEY);

        call.enqueue(this);
    }

    @Override
    public void Dispose() {
        this.repository.Dispose();
    }

    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        if(response.isSuccessful()){
            this.interactorOutput.OnGetDataSuccess(response.body().getResults());
        }else {
            this.interactorOutput.OnGetDataSuccess(this.loadDataFromCache());
            this.interactorOutput.OnGetDataError(response.message());
        }
    }

    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {
        this.interactorOutput.OnGetDataSuccess(this.loadDataFromCache());
        this.interactorOutput.OnGetDataError(t.getMessage());
    }

    private List<Movie> loadDataFromCache(){
        return this.repository.GetDataByTitle(title);
    }
}
