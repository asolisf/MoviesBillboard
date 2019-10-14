package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.repositories.MoviesRespository;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedInteractor extends BaseInteractor
        implements MoviesContract.Interactor, Callback<MoviesResponse> {

    private MoviesContract.InteractorOutput interactorOutput;

    private MoviesRequest moviesRequest;

    private MoviesContract.Respository repository;

    public TopRatedInteractor(MoviesContract.InteractorOutput interactorOutput){
        this.moviesRequest = this.ApiClient.create(MoviesRequest.class);
        this.interactorOutput = interactorOutput;
        this.repository = new MoviesRespository();
    }

    @Override
    public void getData() {
        Call<MoviesResponse> moviesResponseCall = this.moviesRequest.getTopRated(this.API_KEY);

        moviesResponseCall.enqueue(this);
    }

    @Override
    public void Dispose() {
        this.Dispose();
    }

    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        if(response.isSuccessful()){
            this.saveDataInCache(response.body().getResults());
            interactorOutput.onGetDataSuccess(response.body().getResults());
        }else {
            interactorOutput.onGetDataSuccess(this.loadDataFromCache());
            interactorOutput.onGetDataError(response.message());
        }
    }

    @Override
    public void onFailure(Call<MoviesResponse> call, Throwable t) {
        interactorOutput.onGetDataSuccess(this.loadDataFromCache());
        interactorOutput.onGetDataError(t.getMessage());
    }

    private void saveDataInCache(List<Movie> movieList){
        this.repository.SaveDataBySection(movieList,
                Section.TOP_RATED,new Date());

    }

    private List<Movie> loadDataFromCache(){
        return this.repository.GetDataBySection(Section.TOP_RATED);
    }
}
