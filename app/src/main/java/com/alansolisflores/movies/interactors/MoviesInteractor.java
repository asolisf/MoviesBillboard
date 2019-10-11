package com.alansolisflores.movies.interactors;

import com.alansolisflores.movies.contracts.MainContract;
import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.entities.responses.MoviesResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesInteractor extends BaseInteractor implements MainContract.Interactor{

    private MainContract.InteractorOutput interactorOutput;

    private Retrofit retrofit;

    private MoviesRequest moviesRequest;

    public MoviesInteractor(MainContract.InteractorOutput interactorOutput){
        this.interactorOutput = interactorOutput;

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        this.retrofit = new Retrofit.Builder()
                                    .baseUrl(this.ENDPOINT)
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .build();
        this.moviesRequest = this.retrofit.create(MoviesRequest.class);
    }

    @Override
    public void getPopular() {
        Call<MoviesResponse> call = this.moviesRequest.getTopRated(this.API_KEY);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                interactorOutput.onGetPopularSuccess(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                interactorOutput.onGetPopularError(t.getMessage());
            }
        });
    }
}

