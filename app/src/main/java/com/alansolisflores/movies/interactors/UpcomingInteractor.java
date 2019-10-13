package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.MoviesContract;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.MoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpcomingInteractor
        extends BaseInteractor
        implements MoviesContract.Interactor {

    private MoviesContract.InteractorOutput interactorOutput;

    private MoviesRequest moviesRequest;

    public UpcomingInteractor(MoviesContract.InteractorOutput interactorOutput){
        this.interactorOutput = interactorOutput;
        this.moviesRequest = this.ApiClient.create(MoviesRequest.class);
    }

    @Override
    public void getData() {
        Call<MoviesResponse> moviesResponseCall =
                this.moviesRequest.getUpcoming(this.API_KEY);

        moviesResponseCall.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                interactorOutput.onGetDataSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                interactorOutput.onGetDataError(t.getMessage());
            }
        });
    }
}
