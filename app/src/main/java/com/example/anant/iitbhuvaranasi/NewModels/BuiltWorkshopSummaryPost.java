package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltWorkshopSummaryPost {

    @Nullable
    private int id;

    @Nullable
    private ClubListPost club;

    @Nullable
    private String title;

    @Nullable
    private String date;

    @Nullable
    private String time;

    @Nullable
    private List<TagDetail> tags;


    public int getId() {
        return id;
    }

    @Nullable
    public ClubListPost getClub() {
        return club;
    }

    @Nullable
    public String getTitle() {
        return title;
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
    public List<TagDetail> getTags() {
        return tags;
    }
}
