package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rental_buttons extends AppCompatActivity {

    Button addRentalBtn;
    Button editRentalBtn;
    Button deleteRentalBtn;

    public void initAddRental(){

        addRentalBtn = (Button)findViewById(R.id.buttonAddRental);
        addRentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addRental = new Intent(rental_buttons.this, add_rental.class);

                startActivity(addRental);


            }
        });

    }

    public void initEditRental(){

        editRentalBtn = (Button)findViewById(R.id.buttonEditRental);
        editRentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editRental = new Intent(rental_buttons.this, edit_rental.class);

                startActivity(editRental);


            }
        });

    }

    public void initDeleteRental(){

        deleteRentalBtn = (Button)findViewById(R.id.buttonDeleteRental);
        deleteRentalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent deleteRental = new Intent(rental_buttons.this, delete_rental.class);

                startActivity(deleteRental);


            }
        });

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_buttons);

        initAddRental();
        initEditRental();
        initDeleteRental();
    }
}
