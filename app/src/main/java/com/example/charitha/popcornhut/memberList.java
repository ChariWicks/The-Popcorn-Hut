package com.example.charitha.popcornhut;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by CHARITHA on 30-Jul-17.
 */

public class memberList extends ArrayAdapter<Members> {

    private Activity contextMembers;
    List<Members> members;

    public memberList(Activity contextMembers, List<Members> members) {
        super(contextMembers, R.layout.members_list, members);
        this.contextMembers = contextMembers;
        this.members = members;
    }


    @Override
    public View getView(int posiMember, View convertView, ViewGroup parent) {
        LayoutInflater inflater = contextMembers.getLayoutInflater();
        View listViewMember = inflater.inflate(R.layout.members_list, null, true);

        TextView textViewMemFName = (TextView) listViewMember.findViewById(R.id.textViewMemFName);
        TextView textViewMemE = (TextView) listViewMember.findViewById(R.id.textViewMemE);

        Members member = members.get(posiMember);
        textViewMemFName.setText(member.getMemberFName());
        textViewMemE.setText(member.getMemberE());

        return listViewMember;
    }
}
