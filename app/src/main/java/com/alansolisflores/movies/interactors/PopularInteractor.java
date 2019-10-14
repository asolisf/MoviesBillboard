package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.App;
import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.helpers.Config;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularInteractor implements MoviesContract.Interactor, Callback<MoviesResponse>{

    private MoviesContract.InteractorOutput interactorOutput;

    private MoviesContract.Respository repository;

    public PopularInteractor(MoviesContract.InteractorOutput interactorOutput){
        this.interactorOutput = interactorOutput;
        this.repository = new MoviesRespository();
    }

    @Override
    public void GetData() {
        Call<MoviesResponse> call = App.getApiService().getPopular(Config.API_KEY);

        call.enqueue(this);
    }

    @Override
    public void Dispose() {
        this.Dispose();
    }

    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        if(response.isSuccessful()){
            this.saveDataInCache(response.body().getResults());
            this.interactorOutput.OnGetDataSuccess(response.body().getResults());
        }else {
            this.interactorOutput.OnGetDataSuccess(this.loadDataFromCache());
            this.interactorOutput.onGetDataError(response.message());
        }
    }

    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {
        this.interactorOutput.OnGetDataSuccess(this.loadDataFromCache());
        this.interactorOutput.onGetDataError(t.getMessage());
    }

    private void saveDataInCache(List<Movie> movieList){
        this.repository.SaveDataBySection(movieList,
                Section.POPULAR,new Date());

    }

    private List<Movie> loadDataFromCache(){
        return this.repository.GetDataBySection(Section.POPULAR);
    }
}

