package com.example.andriod.popularmoviesstage1;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String API_Key = "534dc6269e7da747bd1aad7cfd13b2bb";
    private TextView mErrorMessageDisplay;
    private static ArrayList<MovieDetails> movieDetailsList;
    private GridView mGridView;
    private MovieDetailsAdapter movieDetailsAdapter;
    Context context;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mErrorMessageDisplay = (TextView) findViewById(R.id.error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mGridView = (GridView) findViewById(R.id.movies_grid);
        context = this ;


        movieDetailsList = new ArrayList<MovieDetails>();
        updateView(movieDetailsList);

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
        mGridView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage(){
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mGridView.setVisibility(View.INVISIBLE);
    }

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

    public void updateView(ArrayList<MovieDetails> movieData){
        movieDetailsAdapter = new MovieDetailsAdapter(this, movieData);
        //Get a reference to the gridView and attach the adapter to it
        GridView mGridView = (GridView) findViewById(R.id.movies_grid);
        mGridView.setAdapter(movieDetailsAdapter);
        //movieDetailsAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_by_pop) {
            new FetchMovieData().execute("http://api.themoviedb.org/3/movie/popular?api_key="+API_Key);
            return true;
        }
        if (id == R.id.sort_by_rating) {
            new FetchMovieData().execute("http://api.themoviedb.org/3/movie/top_rated?api_key="+API_Key);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
