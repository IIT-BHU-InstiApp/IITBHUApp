package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

public class TagCreate {

    @Nullable
    private int id;

    @Nullable
    private String tag_name;

    @Nullable
    private int club;

    public int getId() {
        return id;
    }

    @Nullable
    public String getTag_name() {
        return tag_name;
    }

    public int getClub() {
        return club;
    }
}
