package com.example.andriod.popularmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.andriod.popularmoviesstage1.Adapter.MovieDetails;
import com.example.andriod.popularmoviesstage1.Adapter.MovieDetailsAdapter;
import com.example.andriod.popularmoviesstage1.utilities.NetworkUtils;
import com.example.andriod.popularmoviesstage1.utilities.OpenMovieJsonUtils;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Initilize the API Key here to retrieve the data from the themoviedb.org API
    private static final String API_Key = "534dc6269e7da747bd1aad7cfd13b2bb";

    //URL for retrieving the movies list by popular and top-rated
    private static final String MOVIEDB_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular?api_key=";
    private static final String MOVIEDB_URL_RATING = "http://api.themoviedb.org/3/movie/top_rated?api_key=";

    private GridView mGridView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;

    private static ArrayList<MovieDetails> movieDetailsList;
    private MovieDetailsAdapter movieDetailsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mGridView = (GridView) findViewById(R.id.movies_grid);
        if(savedInstanceState == null || !savedInstanceState.containsKey("movieDataList")) {
            //If there is no savedInstance then create a object and call loadMoviesData to fetch
            movieDetailsList = new ArrayList<MovieDetails>();
            loadMoviesData();
        }
        else {
            //else retrieve the savedInstance and update the adapter
            movieDetailsList = savedInstanceState.getParcelableArrayList("movieDataList");
            updateView(movieDetailsList);
        }

    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movieDataList", movieDetailsList);
        super.onSaveInstanceState(outState);
    }

    /**
     * This method will get tell some
     * background method to get the movies data in the background.
     */
    private void loadMoviesData()
    {
        showMoviesDataView();
        new FetchMovieData().execute(MOVIEDB_URL_POPULAR+API_Key);

    }

    /**
     * This method will make the View for the movie data visible and
     * hide the error message.
     */
    private void showMoviesDataView()
    {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mGridView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the Grid
     * View.
     */
    private void showErrorMessage(){
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mGridView.setVisibility(View.INVISIBLE);
    }

    //Async Task to fetch the data in the background
    public class FetchMovieData extends AsyncTask<String, Void, ArrayList<MovieDetails>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<MovieDetails> doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            URL url = null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            //Get the json response from the URL and parse the JSON and return the MovieDetails as ArrayList
            try {
                String jsonMovieAPIResponse = NetworkUtils
                        .getResponseFromHttpUrl(url);

                movieDetailsList = OpenMovieJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonMovieAPIResponse);

                return movieDetailsList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDetails> movieData){
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            //Update the Gridview if the MovieDetails list is not null
            if(movieData!=null)
            {
                showMoviesDataView();
                updateView(movieData);
            }
            else
            {
                showErrorMessage();
            }

        }
    }

    /**
     * Method to update the GridView and attach an adapter to it
     * This also set an OnItemClickListner for the GridView
     *
     * @param movieData Arraylist of MovieDetails Object
     */
    public void updateView(ArrayList<MovieDetails> movieData){
        movieDetailsAdapter = new MovieDetailsAdapter(this, movieData);
        //Get a reference to the gridView and attach the adapter to it
        GridView mGridView = (GridView) findViewById(R.id.movies_grid);
        mGridView.setAdapter(movieDetailsAdapter);
        //Set a OnItemClickListener for the GridView attached to the Adapter
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Get the MovieDetails Object and pass it to the new activity as parcelble object
                MovieDetails movieDetails = movieDetailsAdapter.getItem(i);
                Intent intent = new Intent(MainActivity.this,MovieDetailActivity.class);
                intent.putExtra("movieDetails",movieDetails);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_by_pop) {
            new FetchMovieData().execute(MOVIEDB_URL_POPULAR+API_Key);
            return true;
        }
        if (id == R.id.sort_by_rating) {
            new FetchMovieData().execute(MOVIEDB_URL_RATING+API_Key);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
