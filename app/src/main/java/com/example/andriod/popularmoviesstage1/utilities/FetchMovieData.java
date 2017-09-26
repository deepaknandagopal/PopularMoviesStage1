package com.example.andriod.popularmoviesstage1.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.example.andriod.popularmoviesstage1.Adapter.MovieDetails;
import com.example.andriod.popularmoviesstage1.Interface.AsyncTaskCompleteListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Separate class is created for performing AsyncTask and used in the mainActivity.java by using Listener
 */

public class FetchMovieData extends AsyncTask<String, Void, ArrayList<MovieDetails>> {

    @SuppressWarnings("FieldCanBeLocal")
    private ArrayList<MovieDetails> movieDetailsList;
    private final AsyncTaskCompleteListener<ArrayList<MovieDetails>> listener;
    private final Context context;

    public FetchMovieData(Context context, AsyncTaskCompleteListener<ArrayList<MovieDetails>> listener)
    {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskInitiate();
    }

    @Override
    protected ArrayList<MovieDetails> doInBackground(String... params) {

        movieDetailsList = new ArrayList<>();
        if (params.length == 0) {
            return null;
        }

        URL url = null;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected)
        {
            //Get the json response from the URL and parse the JSON and return the MovieDetails as ArrayList
            try {
                String jsonMovieAPIResponse = NetworkUtils
                        .getResponseFromHttpUrl(url);

                movieDetailsList = OpenMovieJsonUtils
                        .getSimpleWeatherStringsFromJson(jsonMovieAPIResponse);

                return movieDetailsList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else
            return null;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDetails> movieData) {
        super.onPostExecute(movieData);
        listener.onTaskComplete(movieData);
    }
}
