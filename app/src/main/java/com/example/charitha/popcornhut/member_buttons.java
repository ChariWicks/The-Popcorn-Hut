package com.example.charitha.popcornhut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class member_buttons extends AppCompatActivity {

    Button addMemberBtn;
    Button editMemberBtn;
    Button deleteMemberBtn;

    public void initAddMember(){

        addMemberBtn = (Button)findViewById(R.id.buttonAddMember);
        addMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addMember = new Intent(member_buttons.this, add_member.class);

                startActivity(addMember);


            }
        });
    }

    public void initEditMember(){

        editMemberBtn = (Button)findViewById(R.id.buttonEditMember);
        editMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editMember = new Intent(member_buttons.this, edit_member.class);

                startActivity(editMember);


            }
        });
    }

    public void initDeleteMember(){

        deleteMemberBtn = (Button)findViewById(R.id.buttonDeleteMember);
        deleteMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent deleteMember = new Intent(member_buttons.this, delete_member.class);

                startActivity(deleteMember);


            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_buttons);
        initAddMember();
        initEditMember();
        initDeleteMember();
    }
}
