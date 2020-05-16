package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltProfilePost {

    @Nullable
    private int id;

    @Nullable
    private String name;

    @Nullable
    private String email;

    @Nullable
    private String phone_number;

    @Nullable
    private String department;

    @Nullable
    private String year_of_joining;

    @Nullable
    private List<ClubListPost> subscriptions;

    @Nullable
    private List<ClubListPost> club_privileges;

    @Nullable
    private String photo_url;

    public int getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getPhone_number() {
        return phone_number;
    }

    @Nullable
    public String getDepartment() {
        return department;
    }

    @Nullable
    public String getYear_of_joining() {
        return year_of_joining;
    }

    @Nullable
    public List<ClubListPost> getSubscriptions() {
        return subscriptions;
    }

    @Nullable
    public List<ClubListPost> getClub_privileges() {
        return club_privileges;
    }

    @Nullable
    public String getPhoto_url() {
        return photo_url;
    }
}
