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

public class logInActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth fbAuthLog;

    public Button signupBtn;
    public Button loginBtn;

    public EditText loginEmail;
    public EditText loginPassword;

    private ProgressDialog progressLogIn;

    private void login(){

        String logPassword = loginPassword.getText().toString().trim();
        String logEmail = loginEmail.getText().toString().trim();

        if(TextUtils.isEmpty(logPassword)){

            Toast.makeText(this, "Please enter password ", Toast.LENGTH_SHORT).show();
            return;

        }

        else if(TextUtils.isEmpty(logEmail)){

            Toast.makeText(this, "Please enter an email", Toast.LENGTH_LONG).show();
            return;

        }

        progressLogIn.setMessage("Log In.....");
        progressLogIn.show();

        fbAuthLog.signInWithEmailAndPassword(logEmail, logPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressLogIn.dismiss();

                if(task.isSuccessful()){

                    finish();
                    startActivity(new Intent(getApplicationContext(), button_page.class));

                }

            }





        });



    }

   /* public void init(){

        signupBtn = (Button)findViewById(R.id.button4);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(logInActivity.this, sign_up.class);

                startActivity(signUp);


            }
        });

    }

    public void initLogged(){

        loginBtn = (Button)findViewById(R.id.button3);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login = new Intent(logInActivity.this, button_page.class);

                startActivity(login);


            }
        });

    }*/





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        fbAuthLog = FirebaseAuth.getInstance();

        if(fbAuthLog.getCurrentUser() != null){


            finish();
            startActivity(new Intent(getApplicationContext(), button_page.class));
        }

        loginPassword = (EditText)findViewById(R.id.logPassword);
        loginEmail = (EditText)findViewById(R.id.logEmail);

        signupBtn = (Button)findViewById(R.id.button4);
        loginBtn = (Button)findViewById(R.id.button3);

        progressLogIn = new ProgressDialog(this);

        signupBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        //init();
        //initLogged();


    }

    @Override
    public void onClick(View v) {

        if(v == loginBtn){
            login();
        }

        if(v == signupBtn){
            finish();
            startActivity(new Intent(this, sign_up.class));
        }

    }
}
