package com.example.anant.iitbhuvaranasi;

public class Constants {

    public int i=2;

    public Constants(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public static final String MY_PREFERENCE = "myPref";
    public static final String CALENDAR_DIALOG = "calendar_dialog";
    public static final String CALENDAR_DIALOG_YES = "Yes";
    public static final String CALENDAR_DIALOG_NO = "No";
    public static Boolean isInternetPresent = false;
    public static final String CALENDAR_DIALOG_ALWAYS_ASK = "Always ask";
    public static final String PREF_NAME = "LoggedInPref";
    public static String  Response_Feed_Old= "response_all";
    public static String  Response_Feed_New= "response_all";
}
