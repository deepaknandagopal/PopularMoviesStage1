package com.example.andriod.popularmoviesstage1.Adapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Deepak on 9/23/2017.
 *
 */

public class MovieDetails implements Parcelable {
    private String title;
    private String moviePoster;
    private String overview;
    private String userRating;
    private String releaseDate;
    private int movieID;

    public MovieDetails(String title, String moviePoster, String overview, String userRating, String releaseDate, int movieID) {
        this.title = title;
        this.moviePoster = moviePoster;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.movieID = movieID;
    }

    private MovieDetails(Parcel in){
        title = in.readString();
        moviePoster = in.readString();
        overview = in.readString();
        userRating = in.readString();
        releaseDate = in.readString();
        movieID = in.readInt();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int describeContents(){ return 0;}

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(moviePoster);
        parcel.writeString(overview);
        parcel.writeString(userRating);
        parcel.writeString(releaseDate);
        parcel.writeInt(movieID);
    }

    public static final Parcelable.Creator<MovieDetails> CREATOR = new Parcelable.Creator<MovieDetails>() {
        @Override
        public MovieDetails createFromParcel(Parcel parcel) {return new MovieDetails(parcel);}

        @Override
        public MovieDetails[] newArray(int i) {return new MovieDetails[i];}
    };
}
