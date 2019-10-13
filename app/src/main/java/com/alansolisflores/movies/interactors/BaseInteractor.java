package com.alansolisflores.movies.interactors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseInteractor {

    protected final String ENDPOINT = "https://api.themoviedb.org/3/";

    protected final String API_KEY = "c875d5d3e9f81e2dfe4c02081657b78f";

    protected Retrofit ApiClient;

    public BaseInteractor(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        this.ApiClient = new Retrofit.Builder()
                        .baseUrl(this.ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
    }
}
