package com.example.andriod.popularmoviesstage1.utilities;

import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Scanner;

/**
 * Helper class created for getting response from the web.
 */

final class NetworkUtils {

    @SuppressWarnings("unused")
    private static final String TAG = NetworkUtils.class.getSimpleName();


    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }
    }
}
