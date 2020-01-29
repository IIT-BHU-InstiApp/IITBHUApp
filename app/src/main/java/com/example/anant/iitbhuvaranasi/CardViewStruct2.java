package com.example.anant.iitbhuvaranasi;

public class CardViewStruct2 {
    private int mImageid;
    private String mText2;
    private String mText3;

    public CardViewStruct2(int Imageid, String text2,String text3){
        mImageid = Imageid;
        mText2 = text2;
        mText3 = text3;
    }
    public int getImageid(){
        return mImageid;
    }
    public String getText2(){
        return mText2;
    }
    public String getText3(){
        return mText3;
    }
}