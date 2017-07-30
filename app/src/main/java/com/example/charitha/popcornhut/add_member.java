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

public class add_member extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference fbDatabaseMember;
    private FirebaseAuth fbDatabaseMemberAuth;


    EditText memFName;
    EditText memLName;
    EditText memTel;
    EditText memAdd;
    EditText memE;

    Button memAddDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        fbDatabaseMember = FirebaseDatabase.getInstance().getReference("members");
        fbDatabaseMemberAuth = FirebaseAuth.getInstance();

        memFName = (EditText)findViewById(R.id.txtFName);
        memLName = (EditText)findViewById(R.id.txtLName);
        memTel = (EditText)findViewById(R.id.txtTel);
        memAdd = (EditText)findViewById(R.id.txtAdd);
        memE = (EditText)findViewById(R.id.txtE);

        memAddDB = (Button)findViewById(R.id.buttonAddMemberDB);

        memAddDB.setOnClickListener(this);
    }

    private void addMember(){

        if(fbDatabaseMemberAuth.getCurrentUser() != null){

            String memberFName = memFName.getText().toString().trim();
            String memberLName = memLName.getText().toString().trim();
            String memberTel = memTel.getText().toString().trim();
            String memberAdd = memAdd.getText().toString().trim();
            String memberE = memE.getText().toString().trim();


            if(!TextUtils.isEmpty(memberFName) && !TextUtils.isEmpty(memberLName) && !TextUtils.isEmpty(memberTel) && !TextUtils.isEmpty(memberAdd) && !TextUtils.isEmpty(memberE) ){

                String memberId = fbDatabaseMember.push().getKey();

                Members member = new Members(memberId, memberFName, memberLName, memberTel, memberAdd, memberE);

                fbDatabaseMember.child(memberId).setValue(member);

                memFName.setText("");
                memLName.setText("");
                memTel.setText("");
                memAdd.setText("");
                memE.setText("");

                Toast.makeText(this, "New member is added to the Database", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show();
            }

        }



    }

    @Override
    public void onClick(View v) {


        addMember();

    }
}
