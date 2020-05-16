package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltWorkshopCreatePost {

    private String title;

    private int club;

    private String date;

    @Nullable
    private int id;

    @Nullable
    private String description;

    @Nullable
    private String time;

    @Nullable
    private String location;

    @Nullable
    private String latitude;

    @Nullable
    private String longitude;

    @Nullable
    private String audience;

    @Nullable
    private List<Integer> resources;

    @Nullable
    private List<Integer> contacts;

    @Nullable
    private String image_url;

    @Nullable
    private List<Integer> tags;

    @Nullable
    private String link;

    public String getTitle() {
        return title;
    }

    public int getClub() {
        return club;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public String getTime() {
        return time;
    }

    @Nullable
    public String getLocation() {
        return location;
    }

    @Nullable
    public String getLatitude() {
        return latitude;
    }

    @Nullable
    public String getLongitude() {
        return longitude;
    }

    @Nullable
    public String getAudience() {
        return audience;
    }

    @Nullable
    public List<Integer> getResources() {
        return resources;
    }

    @Nullable
    public List<Integer> getContacts() {
        return contacts;
    }

    @Nullable
    public String getImage_url() {
        return image_url;
    }

    @Nullable
    public List<Integer> getTags() {
        return tags;
    }

    @Nullable
    public String getLink() {
        return link;
    }
}
