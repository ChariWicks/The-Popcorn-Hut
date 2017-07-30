package com.example.charitha.popcornhut;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class delete_member extends AppCompatActivity {

    public static final String MEMBER_NAME = "com.example.charitha.popcornhut.memberFName";
    public static final String MEMBER_ID = "com.example.charitha.popcornhut.memberId";

    DatabaseReference fbDmembers;

    List<Members> memberD;

    ListView listViewMembersD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_member);

        fbDmembers = FirebaseDatabase.getInstance().getReference("members");

        listViewMembersD = (ListView)findViewById(R.id.listDelViewMembers);

        memberD = new ArrayList<>();

        listViewMembersD.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Members member = memberD.get(i);
                showUpdateMemberDialog(member.getMemberId(), member.getMemberFName());
                return true;
            }
        });
    }

    private void showUpdateMemberDialog(final String memberId, String memberFName){

        AlertDialog.Builder dBuilderMem = new AlertDialog.Builder(this);
        LayoutInflater inflaterMem = getLayoutInflater();
        final View dViewMem = inflaterMem.inflate(R.layout.delete_member_form_dialog, null);
        dBuilderMem.setView(dViewMem);

        final Button buttonDelMem = (Button)dViewMem.findViewById(R.id.buttonDeleteMember);

        dBuilderMem.setTitle(memberFName);
        final AlertDialog delDiaMem = dBuilderMem.create();
        delDiaMem.show();

        buttonDelMem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                deleteMember(memberId);
                delDiaMem.dismiss();
            }

        });


    }

    private boolean deleteMember(String id) {

        DatabaseReference dbReefDelMem = FirebaseDatabase.getInstance().getReference("members").child(id);

        dbReefDelMem.removeValue();

        Toast.makeText(getApplicationContext(), "Member details Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart(){

        super.onStart();

        fbDmembers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                memberD.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    Members memberO = postSnapshot.getValue(Members.class);
                    memberD.add(memberO);

                }

                memberList memberAdapter = new memberList(delete_member.this, memberD);
                listViewMembersD.setAdapter(memberAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }


        });



    }


}

