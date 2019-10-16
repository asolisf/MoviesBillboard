package com.alansolisflores.movies.interactors;


import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.VideosResponse;
import com.alansolisflores.movies.helpers.Config;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailInteractor implements DetailContract.Interactor, Callback<VideosResponse> {

    private DetailContract.InteractorOutput interactorOutput;

    private final MoviesContract.Repository moviesRepository;

    private final MoviesRequest moviesRequest;

    @Inject
    public DetailInteractor(DetailContract.InteractorOutput interactorOutput,
                             MoviesContract.Repository moviesRepository,
                             MoviesRequest moviesRequest){
        this.interactorOutput = interactorOutput;
        this.moviesRepository = moviesRepository;
        this.moviesRequest = moviesRequest;
    }

    @Override
    public void GetData(int id) {
        Call<VideosResponse> videosResponseCall =
                this.moviesRequest.getVideos(id, Config.API_KEY);
        videosResponseCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
        if(response.isSuccessful()){
            this.interactorOutput.onGetDataSuccess(response.body().getResults());
        }else {
            this.interactorOutput.onGetDataError(response.message());
        }
    }

    @Override
    public void onFailure(Call<VideosResponse> call, Throwable t) {
        this.interactorOutput.onGetDataError(Config.NETWORK_ERROR);
    }
}
