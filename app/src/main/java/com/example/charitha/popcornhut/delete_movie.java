package com.example.charitha.popcornhut;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class delete_movie extends AppCompatActivity {

    public static final String MOVIE_NAME = "com.example.charitha.popcornhut.moviename";
    public static final String MOVIE_ID = "com.example.charitha.popcornhut.movieid";

    DatabaseReference fbDmovies;

    List<Movies> movieD;

    ListView listViewMoviesD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_movie);

        fbDmovies = FirebaseDatabase.getInstance().getReference("movies");

        listViewMoviesD = (ListView)findViewById(R.id.listDelViewMovies);

        movieD = new ArrayList<>();

        listViewMoviesD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movies movies = movieD.get(i);
                showUpdateMovieDialog(movies.getMovieId(), movies.getMovieName());
                return true;
            }
        });
    }

    private void showUpdateMovieDialog(final String movieId, String movieName){

        AlertDialog.Builder dBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dView = inflater.inflate(R.layout.delete_form_dialog, null);
        dBuilder.setView(dView);

        final Button buttonDeld = (Button)dView.findViewById(R.id.buttonDeleteMovie);

        dBuilder.setTitle(movieName);
        final AlertDialog delDia = dBuilder.create();
        delDia.show();

        buttonDeld.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                deleteArtist(movieId);
                delDia.dismiss();
            }

        });


    }

    private boolean deleteArtist(String id) {

        DatabaseReference dbReefDel = FirebaseDatabase.getInstance().getReference("movies").child(id);

        dbReefDel.removeValue();

        Toast.makeText(getApplicationContext(), "Movie details Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart(){

        super.onStart();

        fbDmovies.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                movieD.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Movies movieO = postSnapshot.getValue(Movies.class);
                    movieD.add(movieO);

                }

                movieList movieAdapter = new movieList(delete_movie.this, movieD);
                listViewMoviesD.setAdapter(movieAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }


        });



    }
}
