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

public class edit_rental extends AppCompatActivity {

    public static final String MEMBER_NAME = "com.example.charitha.popcornhut.membername";
    public static final String RENTAL_ID = "com.example.charitha.popcornhut.rentalid";

    DatabaseReference fbRentals;

    List<Rentals> rental;

    ListView listViewRentals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rental);

        fbRentals = FirebaseDatabase.getInstance().getReference("rentals");

        listViewRentals = (ListView)findViewById(R.id.listViewRentals);

        rental = new ArrayList<>();

        listViewRentals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Rentals rentals = rental.get(i);
                showUpdateRentalDialog(rentals.getRentalId(), rentals.getMovieName());
                return true;
            }
        });




    }

    private boolean editRentals(String rentalId, String movieName,String memberEmail,String issueDate, String dueDate){

        DatabaseReference dbRefRent = FirebaseDatabase.getInstance().getReference("rentals").child(rentalId);

        Rentals rental = new Rentals(rentalId, movieName, memberEmail, issueDate, dueDate);

        dbRefRent.setValue(rental);
        Toast.makeText(getApplicationContext(), "Rental details Updated", Toast.LENGTH_LONG).show();

        return true;


    }

    private void showUpdateRentalDialog(final String rentalId, final String movieName){

        AlertDialog.Builder dBuilderRent = new AlertDialog.Builder(this);
        LayoutInflater inflaterRent = getLayoutInflater();
        final View dViewRent = inflaterRent.inflate(R.layout.update_rental_form_dialog, null);
        dBuilderRent.setView(dViewRent);


        final EditText editTextMovName = (EditText) dViewRent.findViewById(R.id.editTextRentMovNa);
        final EditText editTextMemEmail = (EditText) dViewRent.findViewById(R.id.editTextRentMemE);
        final EditText editTextIssueD = (EditText) dViewRent.findViewById(R.id.editTextIssueD);
        final EditText editTextDueD = (EditText) dViewRent.findViewById(R.id.editTextDueD);

        final Button buttonUpdRental = (Button)dViewRent.findViewById(R.id.buttonUpdateRental);

        dBuilderRent.setTitle(movieName);
        final AlertDialog upDiaRental = dBuilderRent.create();
        upDiaRental.show();

        buttonUpdRental.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String movName = editTextMovName.getText().toString().trim();
                String memEmail = editTextMemEmail.getText().toString().trim();
                String issueD = editTextIssueD.getText().toString().trim();
                String dueD = editTextDueD.getText().toString().trim();

                if(!TextUtils.isEmpty(movName) && !TextUtils.isEmpty(memEmail) && !TextUtils.isEmpty(issueD) && !TextUtils.isEmpty(dueD) ){

                    editRentals(rentalId, movName, memEmail, issueD, dueD);
                    upDiaRental.dismiss();
                }
            }
        });


    }

    @Override
    protected void onStart(){

        super.onStart();

        fbRentals.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                rental.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Rentals rentalO = postSnapshot.getValue(Rentals.class);
                    rental.add(rentalO);

                }

                rentalList rentalAdapter = new rentalList(edit_rental.this, rental);
                listViewRentals.setAdapter(rentalAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }


        });



    }


}
