package com.alansolisflores.movies;

import android.app.Application;
import android.content.Context;

import com.alansolisflores.movies.di.components.ApplicationComponent;
import com.alansolisflores.movies.di.components.DaggerApplicationComponent;
import com.alansolisflores.movies.di.modules.ApplicationModule;

import io.realm.Realm;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        init();
    }

    public static App get(Context context){
        return (App)context.getApplicationContext();
    }

    private void init(){
        Realm.init(this);
    }

    public ApplicationComponent component(){
        return applicationComponent;
    }
}
