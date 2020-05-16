package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltCouncilPost {

    @Nullable
    private int id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private SecyPost gensec;

    @Nullable
    private List<SecyPost> joint_gensec;

    @Nullable
    private List<ClubListPost> clubs;

    @Nullable
    private String small_image_url;

    @Nullable
    private String large_image_url;

    @Nullable
    private boolean is_por_holder;

    @Nullable
    private String website_url;

    @Nullable
    private String facebook_url;

    @Nullable
    private String twitter_url;

    @Nullable
    private String instagram_url;

    @Nullable
    private String linkedin_url;

    @Nullable
    private String youtube_url;

    public int getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @Nullable
    public SecyPost getGensec() {
        return gensec;
    }

    @Nullable
    public List<SecyPost> getJoint_gensec() {
        return joint_gensec;
    }

    @Nullable
    public List<ClubListPost> getClubs() {
        return clubs;
    }

    @Nullable
    public String getSmall_image_url() {
        return small_image_url;
    }

    @Nullable
    public String getLarge_image_url() {
        return large_image_url;
    }

    public boolean isIs_por_holder() {
        return is_por_holder;
    }

    @Nullable
    public String getWebsite_url() {
        return website_url;
    }

    @Nullable
    public String getFacebook_url() {
        return facebook_url;
    }

    @Nullable
    public String getTwitter_url() {
        return twitter_url;
    }

    @Nullable
    public String getInstagram_url() {
        return instagram_url;
    }

    @Nullable
    public String getLinkedin_url() {
        return linkedin_url;
    }

    @Nullable
    public String getYoutube_url() {
        return youtube_url;
    }
}
