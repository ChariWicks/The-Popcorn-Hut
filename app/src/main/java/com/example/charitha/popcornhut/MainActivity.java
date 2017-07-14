package com.example.charitha.popcornhut;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(); //creating an instance of the firebase database
    private DatabaseReference mRootReference = firebaseDatabase.getReference(); //provides reference to the root node
    private DatabaseReference mChildReference = mRootReference.child("me");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
