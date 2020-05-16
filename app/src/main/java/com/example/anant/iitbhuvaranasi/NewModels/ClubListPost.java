package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

public class ClubListPost {

    @Nullable
    private int id;

    @Nullable
    private String name;

    @Nullable
    private BuiltAllCouncilPost council;

    @Nullable
    private String small_image_url;

    @Nullable
    private String large_image_url;

    public int getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public BuiltAllCouncilPost getCouncil() {
        return council;
    }

    @Nullable
    public String getSmall_image_url() {
        return small_image_url;
    }

    @Nullable
    public String getLarge_image_url() {
        return large_image_url;
    }
}
