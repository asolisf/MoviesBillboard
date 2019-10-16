package com.alansolisflores.movies.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.alansolisflores.movies.R;
import com.alansolisflores.movies.adapters.MoviesListItemAdapter;
import com.alansolisflores.movies.components.PresenterComponent;
import com.alansolisflores.movies.contracts.SearchContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.presenters.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchContract.View,
         SearchView.OnQueryTextListener, ListView.OnItemClickListener, View.OnClickListener {

    private List<Movie> movieList;

    private MoviesListItemAdapter moviesListItemAdapter;

    private ListView listView;

    private SearchView searchView;

    private ImageView backImage;

    //@Inject
    SearchPresenter presenter;

    private final int MIN_LENGTH = 2;

    public SearchActivity(){
        //PresenterComponent presenterComponent = DaggerPresenterComponent.create();
        //presenterComponent.Inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.initializeProperties();
    }

    @Override
    public void ShowError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GetData(List<Movie> movieList) {
        this.movieList = movieList;
        this.moviesListItemAdapter
                = new MoviesListItemAdapter(this.movieList,this,R.layout.movie_list_item);
        this.listView.setAdapter(this.moviesListItemAdapter);
        this.listView.setOnItemClickListener(this);
        this.backImage.setOnClickListener(this);
    }

    private void initializeProperties(){
        this.listView = findViewById(R.id.moviesListView);
        this.searchView = findViewById(R.id.searchView);
        this.backImage = findViewById(R.id.backImageView);

        //this.presenter = new SearchPresenter(this);
        this.movieList = new ArrayList<Movie>();

        this.searchView.setOnQueryTextListener(this);
        this.searchView.setFocusable(true);
        this.searchView.setIconified(false);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if(this.searchView.getQuery().toString().length() > this.MIN_LENGTH){
            this.presenter.SearchData(this.searchView.getQuery().toString());
        }else {
            this.ShowError(getResources().getString(R.string.title_min_length));
        }

        this.getCurrentFocus().clearFocus();

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.goToDetailScreen(this.movieList.get(i));
    }

    private void goToDetailScreen(Movie movie){
        Intent intent = new Intent(this,MovieDetailActivity.class);
        intent.putExtra("id",movie.getId());
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("date",movie.getReleaseDate());
        intent.putExtra("image",movie.getPosterPath());
        intent.putExtra("overview",movie.getOverview());
        intent.putExtra("voteAverage",movie.getVoteAverage());
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
