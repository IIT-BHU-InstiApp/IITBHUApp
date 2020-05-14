package com.example.anant.iitbhuvaranasi.Adapters;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.anant.iitbhuvaranasi.Models.AboutCategory;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class AboutAdapter extends SectionedRecyclerViewAdapter {

    public AboutAdapter(List<AboutCategory> categories) {
        super();
        for (AboutCategory category : categories) {
            addSection(category);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
        if (viewHolder instanceof AboutCategory.IndividualViewHolder) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) viewHolder.itemView.getLayoutParams();
            layoutParams.width = (parent.getWidth() / 3) - layoutParams.leftMargin - layoutParams.rightMargin;
            viewHolder.itemView.setLayoutParams(layoutParams);
        }
        return viewHolder;
    }
}
