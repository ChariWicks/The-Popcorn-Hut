package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class button_page extends AppCompatActivity {

    Button movieBtn;
    Button memberBtn;
    Button rentalBtn;

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

        initMovie();
        initMember();
        initRental();
    }
}
