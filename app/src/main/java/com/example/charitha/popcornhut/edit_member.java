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

public class edit_member extends AppCompatActivity {

    public static final String MEMBER_NAME = "com.example.charitha.popcornhut.membername";
    public static final String MEMBER_ID = "com.example.charitha.popcornhut.memberid";

    DatabaseReference fbMembers;

    List<Members> member;

    ListView listViewMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member);

        fbMembers = FirebaseDatabase.getInstance().getReference("members");

        listViewMembers = (ListView)findViewById(R.id.listViewMembers);

        member = new ArrayList<>();

        listViewMembers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Members members = member.get(i);
                showUpdateMemberDialog(members.getMemberId(), members.getMemberFName());
                return true;
            }
        });




    }

    private boolean editMembers(String memberId, String memberFName,String memberLName,String memberTel, String memberAdd, String memberE){

        DatabaseReference dbRefMem = FirebaseDatabase.getInstance().getReference("members").child(memberId);

        Members member = new Members(memberId, memberFName, memberLName, memberTel, memberAdd,memberE);

        dbRefMem.setValue(member);
        Toast.makeText(getApplicationContext(), "Member details Updated", Toast.LENGTH_LONG).show();

        return true;


    }

    private void showUpdateMemberDialog(final String memberId, final String memberFName){

        AlertDialog.Builder dBuilderMem = new AlertDialog.Builder(this);
        LayoutInflater inflaterMem = getLayoutInflater();
        final View dViewMem = inflaterMem.inflate(R.layout.update_member_form_dialog, null);
        dBuilderMem.setView(dViewMem);


        final EditText editTextMemFName = (EditText) dViewMem.findViewById(R.id.editTextMemFName);
        final EditText editTextMemLName = (EditText) dViewMem.findViewById(R.id.editTextMemLName);
        final EditText editTextMemTel = (EditText) dViewMem.findViewById(R.id.editTextMemTel);
        final EditText editTextMemAdd = (EditText) dViewMem.findViewById(R.id.editTextMemAdd);
        final EditText editTextMeme = (EditText) dViewMem.findViewById(R.id.editTextMemE);

        final Button buttonUpdMem = (Button)dViewMem.findViewById(R.id.buttonUpdateMember);

        dBuilderMem.setTitle(memberFName);
        final AlertDialog upDiaMem = dBuilderMem.create();
        upDiaMem.show();

        buttonUpdMem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String memFName = editTextMemFName.getText().toString().trim();
                String memLName = editTextMemLName.getText().toString().trim();
                String memTel = editTextMemTel.getText().toString().trim();
                String memAdd = editTextMemAdd.getText().toString().trim();
                String memE = editTextMeme.getText().toString().trim();

                if(!TextUtils.isEmpty(memFName) && !TextUtils.isEmpty(memLName) && !TextUtils.isEmpty(memTel) && !TextUtils.isEmpty(memAdd) && !TextUtils.isEmpty(memE)){

                    editMembers(memberId, memFName, memLName, memTel, memAdd, memE);
                    upDiaMem.dismiss();
                }
            }
        });


    }

    @Override
    protected void onStart(){

        super.onStart();

        fbMembers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                member.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Members memberO = postSnapshot.getValue(Members.class);
                    member.add(memberO);

                }

                memberList memberAdapter = new memberList(edit_member.this, member);
                listViewMembers.setAdapter(memberAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }


        });



    }


}

