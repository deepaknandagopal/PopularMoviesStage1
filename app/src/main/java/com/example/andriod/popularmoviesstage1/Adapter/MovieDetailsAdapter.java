package com.example.andriod.popularmoviesstage1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andriod.popularmoviesstage1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Deepak on 9/25/2017.
 */

public class MovieDetailsAdapter extends ArrayAdapter<MovieDetails> {

    private static final String LOG_TAG = MovieDetailsAdapter.class.getSimpleName();
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String SIZE = "w185";
    private Context mContext;

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the List is the data we want
     * to populate into the lists
     *
     * @param context The current context. Used to inflate the layout file.
     * @param movieDetailsArrayList A List of MovieDetails objects to display in a ImageView
     */
    public MovieDetailsAdapter(Activity context, ArrayList<MovieDetails> movieDetailsArrayList) {

        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a GridView.
        // Because this is a custom adapter for one ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, movieDetailsArrayList);
        this.mContext = context;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     *                    (search online for "android view recycling" to learn more)
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link MovieDetails} object located at this position in the list
        MovieDetails currentMovieDetail = getItem(position);

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) convertView.findViewById(R.id.list_item_icon);

        //Get the Poster Path from the MovieDetails object
        String posterPath = currentMovieDetail.getMoviePoster();

        //Load the image using picasso api
        Picasso.with(mContext).load(BASE_URL + SIZE + posterPath).into(iconView);

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return convertView;
    }


}
