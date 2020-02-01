package com.example.anant.iitbhuvaranasi;

public class SubscribedNotification {

    private int mImageId;
    private int mNotifications;

    public SubscribedNotification(int ImageResourceId, int NoOfNotifications){
       mImageId = ImageResourceId;
       mNotifications = NoOfNotifications;

    }

    public int getImageId() {
        return mImageId;
    }

    public int getNoOfNotifications() {
        return mNotifications;
    }
}
