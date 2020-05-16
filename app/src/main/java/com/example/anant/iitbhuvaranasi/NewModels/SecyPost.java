package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

public class SecyPost {

    @Nullable
    private int id;

    @Nullable
    private String name;

    @Nullable
    private String email;

    @Nullable
    private String phone_number;

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
    public String getPhoto_url() {
        return photo_url;
    }
}
