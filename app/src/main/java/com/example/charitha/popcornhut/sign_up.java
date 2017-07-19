package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sign_up extends AppCompatActivity {

    public Button signupBtn2;

    public void init(){

        signupBtn2 = (Button)findViewById(R.id.button4);
        signupBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signUpBu = new Intent(sign_up.this, button_page.class);

                startActivity(signUpBu);


            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }
}
