package com.example.andriod.popularmoviesstage1.utilities;


import com.example.andriod.popularmoviesstage1.Adapter.MovieDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Helper class created for parsing the json response from the web.
 */

public final class OpenMovieJsonUtils {

    /**
     * This method parses JSON from a web response and returns an ArrayList of the MovieDetails Objects array of Strings
     * describing various properties of the Movies.
     *
     * @return ArrayList containing MovieDetails object
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<MovieDetails> getSimpleWeatherStringsFromJson(String movieDataJsonStr)
            throws JSONException {

        final String OMD_RESULTS = "results";
        final String OMD_ID = "id";
        final String OMD_TITLE = "title";
        final String OMD_POSTER_PATH = "poster_path";
        final String OMD_OVERVIEW = "overview";
        final String OMD_RELEASE_DATE = "release_date";
        final String OMD_RATING = "vote_average";

        final String OMD_MESSAGE_CODE = "cod";
        ArrayList<MovieDetails> movieDetailsList = new ArrayList<>();


        JSONObject movieDataJson = new JSONObject(movieDataJsonStr);

        /* Is there an error? */
        if (movieDataJson.has(OMD_MESSAGE_CODE)) {
            int errorCode = movieDataJson.getInt(OMD_MESSAGE_CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    /* Location invalid */
                    return null;
                default:
                    /* Server probably down */
                    return null;
            }
        }

        JSONArray MovieDataArray = movieDataJson.getJSONArray(OMD_RESULTS);

        for (int i = 0; i < MovieDataArray.length(); i++) {
            JSONObject singleMovieDetail = MovieDataArray.getJSONObject(i);
            String title = singleMovieDetail.getString(OMD_TITLE);
            String moviePoster = singleMovieDetail.getString(OMD_POSTER_PATH);
            String overview = singleMovieDetail.getString(OMD_OVERVIEW);
            String userRating = singleMovieDetail.getString(OMD_RATING);
            String releaseDate = singleMovieDetail.getString(OMD_RELEASE_DATE);
            int movieID = singleMovieDetail.getInt(OMD_ID);

            movieDetailsList.add(i, new MovieDetails(title, moviePoster, overview, userRating,releaseDate,movieID));
        }

        return movieDetailsList;
    }

}
