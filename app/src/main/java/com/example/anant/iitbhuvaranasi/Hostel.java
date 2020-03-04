package com.example.anant.iitbhuvaranasi;

public class Hostel {

    String mHostelName;
    String mComplainLink;

    public Hostel(String HostelName, String ComplainLink) {
        mHostelName = HostelName;
        mComplainLink = ComplainLink;

    }

    public String getHostelName(){
            return mHostelName;
    }
    public String getComplainLink(){
        return  mComplainLink;
    }


}
