package com.example.charitha.popcornhut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class edit_movie extends AppCompatActivity {

    DatabaseReference fbMmovies;

    List<Movies> movie;

    ListView listViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);

        fbMmovies = FirebaseDatabase.getInstance().getReference("movies");

        listViewMovies = (ListView)findViewById(R.id.listViewMovies);

        movie = new ArrayList<>();


    }

    @Override
    protected void onStart(){

        super.onStart();

        fbMmovies.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                movie.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Movies movieO = postSnapshot.getValue(Movies.class);
                    movie.add(movieO);

                }

                movieList movieAdapter = new movieList(edit_movie.this, movie);
                listViewMovies.setAdapter(movieAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }


        });



    }
}
