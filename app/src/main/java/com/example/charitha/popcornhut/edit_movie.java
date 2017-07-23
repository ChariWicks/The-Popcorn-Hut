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

public class edit_movie extends AppCompatActivity {

    public static final String MOVIE_NAME = "com.example.charitha.popcornhut.moviename";
    public static final String MOVIE_ID = "com.example.charitha.popcornhut.movieid";

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

        listViewMovies.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movies movies = movie.get(i);
                showUpdateMovieDialog(movies.getMovieId(), movies.getMovieName());
                return true;
            }
        });




    }

    private boolean editMovies(String movieId, String movieName,String movieGenre,String movieYear, String movieDuration, String movieLang, String movieDire, String movieMale, String movieFemale){

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("movies").child(movieId);

        Movies movie = new Movies(movieId, movieName, movieGenre, movieYear, movieDuration, movieLang, movieDire, movieMale,movieFemale);

        dbRef.setValue(movie);
        Toast.makeText(getApplicationContext(), "Movie details Updated", Toast.LENGTH_LONG).show();

        return true;


    }

    private void showUpdateMovieDialog(final String movieId, String movieName){

        AlertDialog.Builder dBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dView = inflater.inflate(R.layout.update_form_dialog, null);
        dBuilder.setView(dView);


        final EditText editTextMName = (EditText) dView.findViewById(R.id.editTextMovName);
        final EditText editTextMLang = (EditText) dView.findViewById(R.id.editTextMovLang);
        final EditText editTextMGen = (EditText) dView.findViewById(R.id.editTextMovGen);
        final EditText editTextMYear = (EditText) dView.findViewById(R.id.editTextMovYear);
        final EditText editTextMDur = (EditText) dView.findViewById(R.id.editTextMovDura);
        final EditText editTextMDire = (EditText) dView.findViewById(R.id.editTextMovDire);
        final EditText editTextMMale = (EditText) dView.findViewById(R.id.editTextMovMale);
        final EditText editTextMFe = (EditText) dView.findViewById(R.id.editTextMovFe);

        final Button buttonUpd = (Button)dView.findViewById(R.id.buttonUpdateMovie);

        dBuilder.setTitle(movieName);
        final AlertDialog upDia = dBuilder.create();
        upDia.show();

        buttonUpd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String movName = editTextMName.getText().toString().trim();
                String movLang = editTextMLang.getText().toString().trim();
                String movGen = editTextMGen.getText().toString().trim();
                String movYear = editTextMYear.getText().toString().trim();
                String movDur = editTextMDur.getText().toString().trim();
                String movDir = editTextMDire.getText().toString().trim();
                String movMale = editTextMMale.getText().toString().trim();
                String movFe = editTextMFe.getText().toString().trim();

                if(!TextUtils.isEmpty(movName) && !TextUtils.isEmpty(movGen) && !TextUtils.isEmpty(movLang) && !TextUtils.isEmpty(movYear) && !TextUtils.isEmpty(movDur) && !TextUtils.isEmpty(movDir) && !TextUtils.isEmpty(movMale) && !TextUtils.isEmpty(movFe)){

                    editMovies(movieId,movName,movGen,movYear,movDur,movLang,movDir,movMale,movFe);
                    upDia.dismiss();
                }
            }
        });


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
