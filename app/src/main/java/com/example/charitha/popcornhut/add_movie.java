package com.example.charitha.popcornhut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DataSnapshot;

public class add_movie extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference fbDatabaseMovie;
    private FirebaseAuth fbDatabaseMovieAuth;


    EditText mName;
    EditText mGenre;
    EditText mLang;
    EditText mYear;
    EditText mDuration;
    EditText mDire;
    EditText mMale;
    EditText mFemale;
    Button mAddDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        fbDatabaseMovie = FirebaseDatabase.getInstance().getReference("movies");
        fbDatabaseMovieAuth = FirebaseAuth.getInstance();

        mName = (EditText)findViewById(R.id.txtMovieName);
        mGenre = (EditText)findViewById(R.id.txtMovieGenre);
        mLang = (EditText)findViewById(R.id.txtMovieLanguage);
        mYear = (EditText)findViewById(R.id.txtMovieRelease);
        mDuration = (EditText)findViewById(R.id.txtMovieDuration);
        mDire = (EditText)findViewById(R.id.txtMovieDirector);
        mMale = (EditText)findViewById(R.id.txtMovieMale);
        mFemale = (EditText)findViewById(R.id.txtMovieFemale);

        mAddDB = (Button)findViewById(R.id.buttonAddMovieDB);

        mAddDB.setOnClickListener(this);
    }

    private void addMovie(){

        if(fbDatabaseMovieAuth.getCurrentUser() != null){

            String movieName = mName.getText().toString().trim();
            String movieGen = mGenre.getText().toString().trim();
            String movieLang = mLang.getText().toString().trim();
            String movieYear = mYear.getText().toString().trim();
            String movieDue = mDuration.getText().toString().trim();
            String movieDir = mDire.getText().toString().trim();
            String movieMale = mMale.getText().toString().trim();
            String movieFe = mFemale.getText().toString().trim();

            if(!TextUtils.isEmpty(movieName) && !TextUtils.isEmpty(movieGen) && !TextUtils.isEmpty(movieLang) && !TextUtils.isEmpty(movieYear) && !TextUtils.isEmpty(movieDue) && !TextUtils.isEmpty(movieDir) && !TextUtils.isEmpty(movieMale) && !TextUtils.isEmpty(movieFe)){

                String movId = fbDatabaseMovie.push().getKey();

                Movies movie = new Movies(movId, movieName, movieGen, movieYear, movieDue, movieLang, movieDir, movieMale, movieFe);

                fbDatabaseMovie.child(movId).setValue(movie);

                mName.setText("");
                mGenre.setText("");
                mLang.setText("");
                mYear.setText("");
                mDuration.setText("");
                mDire.setText("");
                mMale.setText("");
                mFemale.setText("");

                Toast.makeText(this, "Movie is added to the Database", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
            }

        }



    }

    @Override
    public void onClick(View v) {


        addMovie();

    }
}
