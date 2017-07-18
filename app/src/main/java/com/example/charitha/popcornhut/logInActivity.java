package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class logInActivity extends AppCompatActivity {

    public Button signupBtn;

    public void init(){

        signupBtn = (Button)findViewById(R.id.button4);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUp = new Intent(logInActivity.this, sign_up.class);

                startActivity(signUp);


            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        init();
    }
}
