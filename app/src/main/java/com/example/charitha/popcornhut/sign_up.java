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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sign_up extends AppCompatActivity implements View.OnClickListener {

    public Button signUpBtnBack;

    //public EditText signUpEmpId;
    public EditText signUpFName;
    public EditText signUpLName;
    public EditText signUpEmail;
    public EditText signUpPassword;

    public FirebaseAuth fbSignUpAuth;
    //public DatabaseReference fbDatabaseRef;


    public ProgressDialog signUpProgress;

    private void mainSignUp(){

        final String regFName = signUpFName.getText().toString().trim();
        final String regLName = signUpLName.getText().toString().trim();
        final String regEmail = signUpEmail.getText().toString().trim();
        String regPassword  = signUpPassword.getText().toString().trim();

        if(TextUtils.isEmpty(regFName) && (TextUtils.isEmpty(regLName) && (TextUtils.isEmpty(regEmail) && (TextUtils.isEmpty(regPassword))))){
            Toast.makeText(this,"Please fill in all the movie details",Toast.LENGTH_LONG).show();
            return;
        }
        else{

            signUpProgress.setMessage("Signing Up....");
            signUpProgress.show();

            fbSignUpAuth.createUserWithEmailAndPassword(regEmail, regPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if(task.isSuccessful()){

                                //String userId = fbSignUpAuth.getCurrentUser().getUid();
                                //System.out.println(userId);

                                //DatabaseReference signedUpUser = fbDatabaseRef.child(userId);
                               // signedUpUser.child("First_name").setValue(regFName);
                                //signedUpUser.child("Last_name").setValue(regLName);
                                //signedUpUser.child("Email").setValue(regEmail);

                                finish();
                                startActivity(new Intent(getApplicationContext(), button_page.class));
                            }else{
                                //display some message here
                                Toast.makeText(sign_up.this,"Registration Error",Toast.LENGTH_LONG).show();
                            }
                            signUpProgress.dismiss();
                        }
                    });

        }




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fbSignUpAuth = FirebaseAuth.getInstance();

       // fbDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");


        //if(fbSignUpAuth.getCurrentUser() != null){

    //        finish();

  //          startActivity(new Intent(getApplicationContext(), button_page.class));
//        }

        signUpFName = (EditText)findViewById(R.id.empFName);
        signUpLName = (EditText)findViewById(R.id.empLName);
        signUpEmail = (EditText) findViewById(R.id.empEmail);
        signUpPassword = (EditText) findViewById(R.id.empPassword);

        signUpBtnBack = (Button) findViewById(R.id.btnSignUp);

        signUpProgress = new ProgressDialog(this);

        signUpBtnBack.setOnClickListener(this);


    }

    public void onClick(View view){

            mainSignUp();



    }






    }

