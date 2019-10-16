package com.alansolisflores.movies.views;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alansolisflores.movies.R;
//import com.alansolisflores.movies.components.DaggerPresenterComponent;
import com.alansolisflores.movies.components.DaggerPresenterComponent;
import com.alansolisflores.movies.contracts.DetailContract;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.entities.objects.Video;
import com.alansolisflores.movies.helpers.Config;
import com.alansolisflores.movies.helpers.BlurTransformation;
import com.alansolisflores.movies.presenters.DetailPresenter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class MovieDetailActivity extends YouTubeBaseActivity
implements View.OnClickListener, DetailContract.View, YouTubePlayer.OnInitializedListener {

    private Movie movie;

    private TextView titleTextView;
    private TextView dateTextView;
    private TextView voteAverageTextView;
    private TextView overviewTextView;
    private ImageView posterImageView;
    private Toolbar customToolbar;
    private ImageView movieBackgroundImageView;
    private YouTubePlayerView playerView;
    private YouTubePlayer youTubePlayer;

    @Inject
    DetailPresenter presenter;

    public MovieDetailActivity(){
        //DaggerPresenterComponent.builder().viewModule(new ViewModule(this)).build();
        DaggerPresenterComponent.builder().build().Inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        this.getExtras();
        this.initializeProperties();
        this.setDataToProperties();
    }

    private void getExtras(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String image = intent.getStringExtra("image");
        String overview = intent.getStringExtra("overview");
        double voteAverage = intent.getDoubleExtra("voteAverage",0);

        this.movie = new Movie();
        this.movie.setId(id);
        this.movie.setTitle(title);
        this.movie.setReleaseDate(date);
        this.movie.setPosterPath(image);
        this.movie.setOverview(overview);
        this.movie.setVoteAverage(voteAverage);
    }

    private void initializeProperties(){
        this.posterImageView = findViewById(R.id.movieImage);
        this.movieBackgroundImageView = findViewById(R.id.movieBackgroundImageView);
        this.titleTextView = findViewById(R.id.movieTitle);
        this.dateTextView = findViewById(R.id.dateTextView);
        this.overviewTextView = findViewById(R.id.overviewTextView);
        this.voteAverageTextView = findViewById(R.id.voteAverageTextView);
        this.customToolbar = findViewById(R.id.customToolbar);
        this.playerView = findViewById(R.id.youtubeView);

        this.customToolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        this.customToolbar.setNavigationOnClickListener(this);
        //this.presenter = new DetailPresenter(this);
        this.playerView.initialize(Config.GOOGLE_KEY,this);
    }

    private void setDataToProperties(){
        Picasso.get()
                .load(Config.IMAGE_ENDPOINT+this.movie.getPosterPath())
                .error(R.drawable.image_not_available)
                .into(this.posterImageView);

        Picasso.get()
                .load(Config.IMAGE_ENDPOINT+this.movie.getPosterPath())
                .fit()
                .centerCrop()
                .transform(new BlurTransformation(this))
                .into(this.movieBackgroundImageView);

        this.titleTextView.setText(this.movie.getTitle());
        this.overviewTextView.setText(this.movie.getOverview());
        this.voteAverageTextView.setText(this.movie.getVoteAverage().toString());
        this.dateTextView.setText(String.format(
                getResources().getString(R.string.release_date),
                this.movie.getReleaseDate()));
        this.customToolbar.setTitle(this.movie.getTitle());
        this.presenter.LoadData(this.movie.getId());
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    public void ShowError(String Message) {
        Toast.makeText(this,Message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void GetData(List<Video> videoList) {
        if(videoList.size() > 0 && this.youTubePlayer != null){
            this.youTubePlayer.cueVideo(videoList.get(0).getId());
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        this.youTubePlayer = youTubePlayer;
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        String error = String.format(getString(R.string.player_error),
                                                youTubeInitializationResult.toString());
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
