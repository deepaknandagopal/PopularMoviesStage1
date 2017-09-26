package com.example.andriod.popularmoviesstage1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andriod.popularmoviesstage1.Adapter.MovieDetails;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    //Url and size for creating the Poster url to fetch the image
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String SIZE = "w185";

    private TextView mTitle;
    private ImageView mPoster;
    private TextView mReleaseDate;
    private TextView mRating;
    private TextView mOverview;
    private MovieDetails movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mTitle = (TextView) findViewById(R.id.movie_title);
        mPoster = (ImageView) findViewById(R.id.movie_poster);
        mRating = (TextView) findViewById(R.id.movie_rating);
        mOverview = (TextView) findViewById(R.id.movie_overview);
        mReleaseDate = (TextView) findViewById(R.id.movie_release_date);

        //Get the MoviesDetail Object from the intent received
        Intent movieDetailIntent = getIntent();
        movieDetails = movieDetailIntent.getParcelableExtra("movieDetails");

        //Set all the TextView details by getting data from the MoviesDetail Object
        mTitle.setText(movieDetails.getTitle());
        mRating.setText(movieDetails.getUserRating());
        mOverview.setText(movieDetails.getOverview());
        String releaseDate = movieDetails.getReleaseDate();
        mReleaseDate.setText(releaseDate);
        mOverview.setText(movieDetails.getOverview());

        // Get the poster path and use Picasso API to display the image
        String posterPath = movieDetails.getMoviePoster();
        Picasso.with(this).load(BASE_URL + SIZE + posterPath).into(mPoster);

    }
}
