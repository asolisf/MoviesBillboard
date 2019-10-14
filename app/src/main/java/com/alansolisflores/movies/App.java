package com.alansolisflores.movies;

import android.app.Application;
import android.content.Context;

import com.alansolisflores.movies.entities.requests.MoviesRequest;
import com.alansolisflores.movies.helpers.Config;
import com.alansolisflores.movies.helpers.NetworkConnectionInterceptor;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static Context context;

    private static MoviesRequest apiService;

    @Override
    public void onCreate() {

        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return App.context;
    }

    public static MoviesRequest getApiService() {
        if (apiService == null) {
            apiService = provideRetrofit(Config.ENDPOINT).create(MoviesRequest.class);
        }
        return apiService;
    }

    private static Retrofit provideRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.addInterceptor(new NetworkConnectionInterceptor(getAppContext()));
        return okhttpClientBuilder.build();
    }
}
