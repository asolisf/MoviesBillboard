package com.alansolisflores.movies.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.alansolisflores.movies.R;
import com.alansolisflores.movies.contracts.MainContract;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeProperties();
    }

    private void initializeProperties(){
        this.tabLayout = findViewById(R.id.tabLayout);
        this.tabLayout.addTab(tabLayout.newTab().setText(R.string.popular));
        this.tabLayout.addTab(tabLayout.newTab().setText(R.string.top_rated));
        this.tabLayout.addTab(tabLayout.newTab().setText(R.string.upcoming));
        this.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        getSupportActionBar().setElevation(0);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
