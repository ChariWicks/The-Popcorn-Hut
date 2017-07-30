package com.example.charitha.popcornhut;

/**
 * Created by CHARITHA on 30-Jul-17.
 */

public class Rentals {

    private String rentalId;
    private String movieName;
    private String memberEmail;
    private String rentalIssue;
    private String rentalDue;

    public Rentals(){

    }

    public Rentals(String rentalId, String movieName,String memberEmail,String rentalIssue, String rentalDue){

        this.rentalId = rentalId;
        this.movieName = movieName;
        this.memberEmail = memberEmail;
        this.rentalIssue = rentalIssue;
        this.rentalDue = rentalDue;
    }

    public String getRentalId(){
        return rentalId;
    }

    public String getMovieName(){
        return movieName;
    }

    public String getMemberEmail(){
        return memberEmail;
    }

    public String getRentalIssue(){
        return  rentalIssue;
    }

    public String getRentalDue(){
        return rentalDue;
    }

}
