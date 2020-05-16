package com.example.anant.iitbhuvaranasi.NewModels;

import androidx.annotation.Nullable;

import java.util.List;

public class BuiltTeamMemberPost {
    @Nullable
    private String  role;

    @Nullable
    private List<TeamMember> team_members;

    @Nullable
    public String getRole() {
        return role;
    }

    @Nullable
    public List<TeamMember> getTeam_members() {
        return team_members;
    }
}
