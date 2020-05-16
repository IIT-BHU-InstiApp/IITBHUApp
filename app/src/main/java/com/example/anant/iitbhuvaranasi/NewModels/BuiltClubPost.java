package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltClubPost {

    @Nullable
    private int id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private BuiltAllCouncilsPost council;

    @Nullable
    private SecyPost secy;

    @Nullable
    private List<SecyPost> joint_secy;

    @Nullable
    private String small_image_url;

    @Nullable
    private String large_image_url;

    @Nullable
    private boolean is_subscribed;

    @Nullable
    private int subscribed_users;

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
    public BuiltAllCouncilsPost getCouncil() {
        return council;
    }

    @Nullable
    public SecyPost getSecy() {
        return secy;
    }

    @Nullable
    public List<SecyPost> getJoint_secy() {
        return joint_secy;
    }

    @Nullable
    public String getSmall_image_url() {
        return small_image_url;
    }

    @Nullable
    public String getLarge_image_url() {
        return large_image_url;
    }

    @Nullable
    public boolean getIs_subscribed() {
        return is_subscribed;
    }

    public int getSubscribed_users() {
        return subscribed_users;
    }

    @Nullable
    public boolean getIs_por_holder() {
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
