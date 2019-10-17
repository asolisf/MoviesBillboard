package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.TopRatedContract;
import com.alansolisflores.movies.mocks.repositories.entities.enums.Section;
import com.alansolisflores.movies.mocks.repositories.entities.objects.Movie;
import com.alansolisflores.movies.mocks.repositories.entities.requests.MoviesRequest;
import com.alansolisflores.movies.mocks.repositories.entities.responses.MoviesResponse;
import com.alansolisflores.movies.helpers.Config;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedInteractor implements TopRatedContract.Interactor, Callback<MoviesResponse> {

    private TopRatedContract.InteractorOutput interactorOutput;

    private final TopRatedContract.Repository repository;

    private final MoviesRequest moviesRequest;

    @Inject
    public TopRatedInteractor(TopRatedContract.Repository repository,
                             MoviesRequest moviesRequest){
        this.repository = repository;
        this.moviesRequest = moviesRequest;
    }
    @Override
    public void GetData() {
        Call<MoviesResponse> moviesResponseCall
                = moviesRequest.getTopRated(Config.API_KEY);

        moviesResponseCall.enqueue(this);
    }

    @Override
    public void Dispose() {
        this.repository.Dispose();
    }

    @Override
    public void Subscribe(TopRatedContract.InteractorOutput interactorOutput) {
        this.interactorOutput = interactorOutput;
    }

    @Override
    public void Unsubscribe() {
        this.interactorOutput = null;
    }

    @Override
    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
        if(response.isSuccessful()){
            this.saveDataInCache(response.body().getResults());
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

    private void saveDataInCache(List<Movie> movieList){
        this.repository.SaveDataBySection(movieList,
                Section.TOP_RATED,new Date());

    }

    private List<Movie> loadDataFromCache(){
        return this.repository.GetDataBySection(Section.TOP_RATED);
    }
}
