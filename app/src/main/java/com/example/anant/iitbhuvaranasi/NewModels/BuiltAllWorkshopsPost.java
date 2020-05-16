package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltAllWorkshopsPost {

    @Nullable
    List<BuiltWorkshopSummaryPost>  active_workshops;

    @Nullable
    List<BuiltWorkshopSummaryPost>  past_workshops;

    @Nullable
    public List<BuiltWorkshopSummaryPost> getActive_workshops() {
        return active_workshops;
    }

    @Nullable
    public List<BuiltWorkshopSummaryPost> getPast_workshops() {
        return past_workshops;
    }
}
