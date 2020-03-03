package com.example.anant.iitbhuvaranasi;

public class ContactsStruct {
    private String mName;
    private String mPost;
    private String mNumber;
    private String mEmail;


    public ContactsStruct(String post, String name, String number, String email){
        mName = name;
        mPost = post;
        mEmail = email;
        mNumber = number;
    }

    public String getPost(){
        return mPost;
    }
    public String getNumber(){
        return mNumber;
    }
    public String getEmail(){
        return mEmail;
    }
    public String getName(){
        return mName;
    }

}
