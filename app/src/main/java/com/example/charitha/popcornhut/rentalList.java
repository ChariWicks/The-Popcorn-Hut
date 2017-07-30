package com.example.charitha.popcornhut;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CHARITHA on 30-Jul-17.
 */

public class rentalList extends ArrayAdapter<Rentals> {

    private Activity contextRentals;
    List<Rentals> rentals;

    public rentalList(Activity contextRentals, List<Rentals> rentals) {
        super(contextRentals, R.layout.rental_list, rentals);
        this.contextRentals = contextRentals;
        this.rentals = rentals;
    }


    @Override
    public View getView(int posiRental, View convertView, ViewGroup parent) {
        LayoutInflater inflater = contextRentals.getLayoutInflater();
        View listViewRental = inflater.inflate(R.layout.rental_list, null, true);

        TextView textViewMovName = (TextView) listViewRental.findViewById(R.id.textViewReMovName);
        TextView textViewMeE = (TextView) listViewRental.findViewById(R.id.textViewReMeE);
        TextView textViewIssue = (TextView) listViewRental.findViewById(R.id.textViewIssue);
        TextView textViewDue = (TextView) listViewRental.findViewById(R.id.textViewDue);

        Rentals rental = rentals.get(posiRental);
        textViewMovName.setText(rental.getMovieName());
        textViewMeE.setText(rental.getMemberEmail());
        textViewIssue.setText("Issue Date " + rental.getRentalIssue());
        textViewDue.setText("Due Date " + rental.getRentalDue());

        return listViewRental;
    }
}
