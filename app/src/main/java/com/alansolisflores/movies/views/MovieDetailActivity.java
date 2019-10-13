package com.alansolisflores.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alansolisflores.movies.R;
import com.alansolisflores.movies.entities.objects.Movie;
import com.alansolisflores.movies.helpers.Constants;
import com.alansolisflores.movies.transformations.BlurTransformation;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity
implements View.OnClickListener{

    private Movie movie;

    private TextView titleTextView;
    private TextView dateTextView;
    private TextView voteAverageTextView;
    private TextView overviewTextView;
    private ImageView posterImageView;
    private Toolbar customToolbar;
    private ImageView movieBackgroundImageView;

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
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String image = intent.getStringExtra("image");
        String overview = intent.getStringExtra("overview");
        double voteAverage = intent.getDoubleExtra("voteAverage",0);

        this.movie = new Movie();
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

        this.customToolbar.setNavigationIcon(R.drawable.ic_left_arrow);
        this.customToolbar.setNavigationOnClickListener(this);
    }

    private void setDataToProperties(){
        Picasso.get()
                .load(Constants.IMAGE_ENDPOINT+this.movie.getPosterPath())
                .error(R.drawable.image_not_available)
                .into(this.posterImageView);

        Picasso.get()
                .load(Constants.IMAGE_ENDPOINT+this.movie.getPosterPath())
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
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
