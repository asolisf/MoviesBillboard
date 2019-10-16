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
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopRatedInteractor implements MoviesContract.Interactor, Callback<MoviesResponse> {

    private MoviesContract.InteractorOutput interactorOutput;

    private final MoviesContract.Repository repository;

    private final MoviesRequest moviesRequest;

    @Inject
    public TopRatedInteractor(@Named("InteractorOutputPresenter") MoviesContract.InteractorOutput interactorOutput,
                             MoviesContract.Repository repository,
                             MoviesRequest moviesRequest){
        this.interactorOutput = interactorOutput;
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
