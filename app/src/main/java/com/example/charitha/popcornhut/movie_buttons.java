package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class movie_buttons extends AppCompatActivity {

    public Button addMovieBtn;
    public Button editMovieBtn;
    public Button deleteMovieBtn;

    public void initAddMovie(){

        addMovieBtn = (Button)findViewById(R.id.buttonAddMovie);
        addMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addMovie = new Intent(movie_buttons.this, add_movie.class);

                startActivity(addMovie);


            }
        });
    }

    public void initEditMovie(){

        editMovieBtn = (Button)findViewById(R.id.buttonEditMovie);
        editMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editMovie = new Intent(movie_buttons.this, edit_movie.class);

                startActivity(editMovie);


            }
        });
    }

    public void initDeleteMovie(){

        deleteMovieBtn = (Button)findViewById(R.id.buttonDeleteMovie);
        deleteMovieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent deleteMovie = new Intent(movie_buttons.this, delete_movie.class);

                startActivity(deleteMovie);


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_buttons);

        initAddMovie();
        initEditMovie();
        initDeleteMovie();
    }
}
