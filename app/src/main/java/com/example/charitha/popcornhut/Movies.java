package com.example.charitha.popcornhut;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by CHARITHA on 22-Jul-17.
 */

public class Movies {

    private String movieId;
    private String movieName;
    private String movieGenre;
    private String movieYear;
    private String movieDuration;
    private String movieLang;
    private String movieDire;
    private String movieMale;
    private String movieFemale;

    public Movies(){

    }

    public Movies(String movieId, String movieName,String movieGenre,String movieYear, String movieDuration, String movieLang, String movieDire, String movieMale, String movieFemale){

        this.movieId = movieId;
        this.movieName = movieName;
        this.movieGenre = movieGenre;
        this.movieYear = movieYear;
        this.movieDuration = movieDuration;
        this.movieLang = movieLang;
        this.movieDire = movieDire;
        this.movieMale = movieMale;
        this.movieFemale = movieFemale;
    }

    public String getMovieId(){
        return movieId;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getMovieGenre(){
        return movieGenre;
    }

    public String getMovieYear(){
        return  movieYear;
    }

    public String getMovieDuration(){
        return movieDuration;
    }

    public String getMovieLang(){
        return movieLang;
    }

    public String getMovieDire(){
        return movieDire;
    }

    public String getMovieMale() {
        return movieMale;
    }

    public String getMovieFemale() {
        return movieFemale;
    }
}
