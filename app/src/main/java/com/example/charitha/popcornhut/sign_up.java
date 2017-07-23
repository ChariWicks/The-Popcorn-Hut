package com.example.charitha.popcornhut;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity {

    public Button signupBtn2;

    public EditText signUpEmpId;
    public EditText signUpFName;
    public EditText signUpLName;
    public EditText signUpEmail;
    public EditText signUpPassword;

    public FirebaseAuth mAuth;
    public DatabaseReference mDatabase;

    public ProgressDialog mProgress;

    public void init(){


        Intent signUpBu = new Intent(sign_up.this, button_page.class);
        signUpBu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(signUpBu);


    }

    public void startSignUp(){

        String empId = signUpEmpId.getText().toString().trim();
        final String empFName = signUpFName.getText().toString().trim();
        final String empLName = signUpLName.getText().toString().trim();
        final String empEmail = signUpEmail.getText().toString().trim();
        final String empPass = signUpPassword.getText().toString().trim();

        if(!TextUtils.isEmpty(empId) && !TextUtils.isEmpty(empFName) && !TextUtils.isEmpty(empLName) && !TextUtils.isEmpty(empEmail) && !TextUtils.isEmpty(empPass)){

            mProgress.setMessage("Signing Up...");
            mProgress.show();

            mAuth.createUserWithEmailAndPassword(empEmail, empPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){

                        String user_id = mAuth.getCurrentUser().getUid();

                        DatabaseReference currentUserDB = mDatabase.child(user_id);
                        currentUserDB.child("eFName").setValue(empFName);
                        currentUserDB.child("eLName").setValue(empLName);
                        currentUserDB.child("eEmail").setValue(empEmail);
                        currentUserDB.child("ePassword").setValue(empPass);

                        mProgress.dismiss();

                        init();

                    }

                }
            });


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("administrators");

        mProgress = new ProgressDialog(this);

        //signUpEmpId = (EditText)findViewById(R.id.empId);
        signUpFName = (EditText)findViewById(R.id.empFName);
        signUpLName = (EditText)findViewById(R.id.empLName);
        signUpEmail = (EditText)findViewById(R.id.empEmail);
        signUpPassword = (EditText)findViewById(R.id.empPassword);

        signupBtn2 = (Button)findViewById(R.id.button4);

        signupBtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startSignUp();




            }
        });


    }






    }

