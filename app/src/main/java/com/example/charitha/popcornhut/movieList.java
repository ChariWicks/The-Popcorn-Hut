package com.example.charitha.popcornhut;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by CHARITHA on 23-Jul-17.
 */

public class movieList extends ArrayAdapter<Movies> {

    private Activity contextMovies;
    List<Movies> movies;

    public movieList(Activity contextMovies, List<Movies> movies) {
        super(contextMovies, R.layout.layout_movies_list, movies);
        this.contextMovies = contextMovies;
        this.movies = movies;
    }


    @Override
    public View getView(int posiMovie, View convertView, ViewGroup parent) {
        LayoutInflater inflater = contextMovies.getLayoutInflater();
        View listViewMovie = inflater.inflate(R.layout.layout_movies_list, null, true);

        TextView textViewMovName = (TextView) listViewMovie.findViewById(R.id.textViewMovName);
        TextView textViewMovGenre = (TextView) listViewMovie.findViewById(R.id.textViewMovLan);

        Movies movie = movies.get(posiMovie);
        textViewMovName.setText(movie.getMovieName());
        textViewMovGenre.setText(movie.getMovieLang());

        return listViewMovie;
    }
}
