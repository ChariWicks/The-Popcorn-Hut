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

public class add_rental extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference fbDatabaseRental;
    private FirebaseAuth fbDatabaseRentalAuth;


    EditText rentMovName;
    EditText rentMemEmail;
    EditText rentIssue;
    EditText rentDue;

    Button rentAddDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rental);

        fbDatabaseRental = FirebaseDatabase.getInstance().getReference("rentals");
        fbDatabaseRentalAuth = FirebaseAuth.getInstance();

        rentMovName = (EditText)findViewById(R.id.txtMoName);
        rentMemEmail = (EditText)findViewById(R.id.txtMemberEm);
        rentIssue = (EditText)findViewById(R.id.txtIssueDat);
        rentDue = (EditText)findViewById(R.id.txtDueDat);

        rentAddDB = (Button)findViewById(R.id.buttonAddRentalDB);

        rentAddDB.setOnClickListener(this);
    }

    private void addRental(){

        if(fbDatabaseRentalAuth.getCurrentUser() != null){

            String rntMovN = rentMovName.getText().toString().trim();
            String rntMemE = rentMemEmail.getText().toString().trim();
            String rntIsD = rentIssue.getText().toString().trim();
            String rntDuD = rentDue.getText().toString().trim();


            if(!TextUtils.isEmpty(rntMovN) && !TextUtils.isEmpty(rntMemE) && !TextUtils.isEmpty(rntIsD) && !TextUtils.isEmpty(rntDuD)){

                String rentalId = fbDatabaseRental.push().getKey();

                Rentals rent = new Rentals(rentalId, rntMovN, rntMemE, rntIsD, rntDuD);

                fbDatabaseRental.child(rentalId).setValue(rent);

                rentMovName.setText("");
                rentMemEmail.setText("");
                rentIssue.setText("");
                rentDue.setText("");

                Toast.makeText(this, "New rental is added to the Database", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
            }

        }



    }

    @Override
    public void onClick(View v) {


        addRental();

    }
}

