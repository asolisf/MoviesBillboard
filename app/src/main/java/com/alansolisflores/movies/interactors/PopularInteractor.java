package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.enums.Section;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.alansolisflores.movies.helpers.Config;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularInteractor implements MoviesContract.Interactor, Callback<MoviesResponse>{

    private MoviesContract.InteractorOutput interactorOutput;

    private final MoviesContract.Repository moviesRepository;

    private final MoviesRequest moviesRequest;

    @Inject
    public PopularInteractor(MoviesContract.InteractorOutput interactorOutput,
                             MoviesContract.Repository moviesRepository,
                             MoviesRequest moviesRequest){
        this.interactorOutput = interactorOutput;
        this.moviesRepository = moviesRepository;
        this.moviesRequest = moviesRequest;
    }

    @Override
    public void GetData() {
        Call<MoviesResponse> call = this.moviesRequest.getPopular(Config.API_KEY);

        call.enqueue(this);
    }

    @Override
    public void Dispose() {
        this.moviesRepository.Dispose();
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
        this.moviesRepository.SaveDataBySection(movieList,
                Section.POPULAR,new Date());
    }

    private List<Movie> loadDataFromCache(){
        return this.moviesRepository.GetDataBySection(Section.POPULAR);
    }
}

