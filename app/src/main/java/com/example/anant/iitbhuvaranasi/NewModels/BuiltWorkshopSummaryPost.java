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


    public BuiltWorkshopSummaryPost(
            int id,
            @Nullable ClubListPost club,
            @Nullable String title,
            @Nullable String date,
            @Nullable String time,
            @Nullable List<TagDetail> tags
    ) {
        this.id = id;
        this.club = club;
        this.title = title;
        this.date = date;
        this.time = time;
        this.tags = tags;
    }


}
