package com.example.andriod.popularmoviesstage1.utilities;

import android.content.Context;

import com.example.andriod.popularmoviesstage1.Adapter.MovieDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak on 9/23/2017.
 */

public final class OpenMovieJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     *
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static String[] getSimpleWeatherStringsFromJson(Context context, String movieDataJsonStr, ArrayList<MovieDetails> movieDetailsList)
            throws JSONException {

        final String OMD_RESULTS = "results";
        final String OMD_ID = "id";
        final String OMD_TITLE = "title";
        final String OMD_POSTER_PATH = "poster_path";
        final String OMD_OVERVIEW = "overview";
        final String OMD_RELEASE_DATE = "release_date";

        final String OMD_MESSAGE_CODE = "cod";
        String name = null;
//
//        /* Weather information. Each day's forecast info is an element of the "list" array */
//        final String OWM_LIST = "list";
//
//
//        /* All temperatures are children of the "temp" object */
//        final String OWM_TEMPERATURE = "temp";
//
//        /* Max temperature for the day */
//        final String OWM_MAX = "max";
//        final String OWM_MIN = "min";
//
//        final String OWM_WEATHER = "weather";
//        final String OWM_DESCRIPTION = "main";



        /* String array to hold each day's weather String */
        String[] parsedWeatherData = null;

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

        //parsedWeatherData = new String[weatherArray.length()];

        long localDate = System.currentTimeMillis();
        for (int i = 0; i < MovieDataArray.length(); i++) {
//            String date;
//            String highAndLow;
//
//            /* These are the values that will be collected */
//            long dateTimeMillis;
//            double high;
//            double low;
//            String description;
//
//            /* Get the JSON object representing the day */
//            JSONObject dayForecast = weatherArray.getJSONObject(i);
//
//            /*
//             * We ignore all the datetime values embedded in the JSON and assume that
//             * the values are returned in-order by day (which is not guaranteed to be correct).
//             */
//
//            /*
//             * Description is in a child array called "weather", which is 1 element long.
//             * That element also contains a weather code.
//             */
//            JSONObject weatherObject =
//                    dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
//            description = weatherObject.getString(OWM_DESCRIPTION);
//
//            /*
//             * Temperatures are sent by Open Weather Map in a child object called "temp".
//             *
//             * Editor's Note: Try not to name variables "temp" when working with temperature.
//             * It confuses everybody. Temp could easily mean any number of things, including
//             * temperature, temporary and is just a bad variable name.
//             */
//            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
//            high = temperatureObject.getDouble(OWM_MAX);
//            low = temperatureObject.getDouble(OWM_MIN);
//
//
//            parsedWeatherData[i] =  description;
        }

        return parsedWeatherData;
    }

}
