package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

public class TagDetail {

    @Nullable
    private int id;

    @Nullable
    private String tag_name;

    @Nullable
    private ClubListPost club;

    public int getId() {
        return id;
    }

    @Nullable
    public String getTag_name() {
        return tag_name;
    }

    @Nullable
    public ClubListPost getClub() {
        return club;
    }
}
