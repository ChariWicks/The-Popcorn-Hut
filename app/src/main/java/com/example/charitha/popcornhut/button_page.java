package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class button_page extends AppCompatActivity implements View.OnClickListener {

    Button movieBtn;
    Button memberBtn;
    Button rentalBtn;

    private FirebaseAuth btFirebase;
    private Button btLogOut;
    private TextView textWelcome;

    @Override
    public void onClick(View view) {

        if(view == btLogOut){
            btFirebase.signOut();
            finish();
            startActivity(new Intent(this, logInActivity.class));
        }
    }

    public void initMovie(){

        movieBtn = (Button)findViewById(R.id.buttonMovies);
        movieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent movie = new Intent(button_page.this, movie_buttons.class);

                startActivity(movie);


            }
        });
    }

    public void initMember(){

        memberBtn = (Button)findViewById(R.id.buttonMembers);
        memberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent member = new Intent(button_page.this, member_buttons.class);

                startActivity(member);


            }
        });
    }

    public void initRental(){

        rentalBtn = (Button)findViewById(R.id.buttonRentals);
        rentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent rental = new Intent(button_page.this, rental_buttons.class);

                startActivity(rental);


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_page);

        btFirebase = FirebaseAuth.getInstance();

        if(btFirebase.getCurrentUser() == null){

            finish();

            startActivity(new Intent(this, logInActivity.class));

        }

        FirebaseUser currntUs = btFirebase.getCurrentUser();

        btLogOut = (Button)findViewById(R.id.buttonSignOut);
        textWelcome = (TextView)findViewById(R.id.txtLoggedMsg) ;

        textWelcome.setText("                      Welcome " + currntUs.getEmail());

        btLogOut.setOnClickListener(this);


        initMovie();
        initMember();
        initRental();
    }
}
