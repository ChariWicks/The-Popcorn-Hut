package com.example.charitha.popcornhut;

/**
 * Created by CHARITHA on 30-Jul-17.
 */

public class Members {

    private String memberId;
    private String memberFName;
    private String memberLName;
    private String memberTel;
    private String memberAdd;
    private String memberE;

    public Members(){

    }

    public Members(String memberId, String memberFName,String memberLName,String memberTel, String memberAdd, String memberE){

        this.memberId = memberId;
        this.memberFName = memberFName;
        this.memberLName = memberLName;
        this.memberTel = memberTel;
        this.memberAdd = memberAdd;
        this.memberE = memberE;
    }

    public String getMemberId(){
        return memberId;
    }

    public String getMemberFName(){
        return memberFName;
    }

    public String getMemberLName(){
        return memberLName;
    }

    public String getMemberTel(){
        return  memberTel;
    }

    public String getMemberAdd(){
        return memberAdd;
    }

    public String getMemberE(){
        return memberE;
    }

}
