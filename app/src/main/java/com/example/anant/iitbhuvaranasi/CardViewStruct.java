package com.example.anant.iitbhuvaranasi;

public class CardViewStruct {
    private String mText1;
    private String mText2;
    private String mText3;

    public CardViewStruct(String text1, String text2,String text3){
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
    }
    public String getText1(){
        return mText1;
    }
    public String getText2(){
        return mText2;
    }
    public String getText3(){
        return mText3;
    }
}

