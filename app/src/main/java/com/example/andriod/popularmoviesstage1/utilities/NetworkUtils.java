package com.example.andriod.popularmoviesstage1.utilities;

import java.io.InputStream;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Scanner;

/**
 * Created by Deepak on 9/23/2017.
 */

public final class NetworkUtils {

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
