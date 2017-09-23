package com.example.andriod.popularmoviesstage1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.andriod.popularmoviesstage1.Adapter.MovieDetails;
import com.example.andriod.popularmoviesstage1.utilities.NetworkUtils;
import com.example.andriod.popularmoviesstage1.utilities.OpenMovieJsonUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String API_Key = "534dc6269e7da747bd1aad7cfd13b2bb";
    private TextView mErrorMessageDisplay;
    private static ArrayList<MovieDetails> movieDetailsList;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieDetailsList= new ArrayList<MovieDetails>();

        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadMoviesData();
    }

    private void loadMoviesData()
    {
        showMoviesDataView();

        new FetchMovieData().execute("http://api.themoviedb.org/3/movie/popular?api_key="+API_Key);

    }

    private void showMoviesDataView()
    {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mLoadingIndicator.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage(){
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    public class FetchMovieData extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            URL url = null;
            try {
                url = new URL(params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                String jsonMovieAPIResponse = NetworkUtils
                        .getResponseFromHttpUrl(url);

                String[] simpleJsonMovieData = OpenMovieJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, jsonMovieAPIResponse, movieDetailsList);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] weatherData){

        }
    }
}
