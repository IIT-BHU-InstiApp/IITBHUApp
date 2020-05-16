package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltWorkshopDetailPost {

    @Nullable
   private int  id;

    @Nullable
    private    String  title;

    @Nullable
    private   String  description;

    @Nullable
    private   ClubListPost  club;

    @Nullable
    private   String  date;

    @Nullable
    private   String  time;

    @Nullable
    private   String  location;

    @Nullable
    private   String  latitude;

    @Nullable
    private   String  longitude;

    @Nullable
    private   String  audience;

    @Nullable
    private   List<WorkshopResources> resources;

    @Nullable
    private   List<ContactPost>  contacts;

    @Nullable
    private   String  image_url;

    @Nullable
    private   boolean  is_interested;

    @Nullable
    private   int  interested_users;

    @Nullable
    private   boolean  is_workshop_contact;

    @Nullable
    private   boolean  is_por_holder;

    @Nullable
    private   List<TagDetail>  tags;

    @Nullable
    private   String  link;

    public int getId() {
        return id;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public ClubListPost getClub() {
        return club;
    }

    @Nullable
    public String getDate() {
        return date;
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
    public List<WorkshopResources> getResources() {
        return resources;
    }

    @Nullable
    public List<ContactPost> getContacts() {
        return contacts;
    }

    @Nullable
    public String getImage_url() {
        return image_url;
    }

    public boolean isIs_interested() {
        return is_interested;
    }

    public int getInterested_users() {
        return interested_users;
    }

    public boolean isIs_workshop_contact() {
        return is_workshop_contact;
    }

    public boolean isIs_por_holder() {
        return is_por_holder;
    }

    @Nullable
    public List<TagDetail> getTags() {
        return tags;
    }

    @Nullable
    public String getLink() {
        return link;
    }
}
