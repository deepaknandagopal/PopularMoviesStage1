package com.example.andriod.popularmoviesstage1.Adapter;

import java.util.Date;

/**
 * Created by Deepak on 9/23/2017.
 */

public class MovieDetails {
    private String title;
    private String moviePoster;
    private String overview;
    private String userRating;
    private Date releaseDate;
    private int movieID;

    public MovieDetails(String title, String moviePoster, String overview, String userRating, Date releaseDate, int movieID) {
        this.title = title;
        this.moviePoster = moviePoster;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
        this.movieID = movieID;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
}
