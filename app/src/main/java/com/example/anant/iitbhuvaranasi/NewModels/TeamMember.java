package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

public class TeamMember {

    @Nullable
    private String name;

    @Nullable
    private String github_username;

    @Nullable
    private String github_image_url;

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getGithub_username() {
        return github_username;
    }

    @Nullable
    public String getGithub_image_url() {
        return github_image_url;
    }
}
