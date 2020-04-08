package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class AboutCategory extends StatelessSection {

    private String name;
    private List<AboutIndividual> individuals;
    private Context context;

    public AboutCategory(String name, List<AboutIndividual> individuals, Context context) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.about_individual_card)
                .headerResourceId(R.layout.about_category_card)
                .build());
        this.name = name;
        this.individuals = individuals;
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AboutIndividual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<AboutIndividual> individuals) {
        this.individuals = individuals;
    }

    @Override
    public int getContentItemsTotal() {
        return individuals.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new IndividualViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final AboutIndividual individual = individuals.get(position);
        IndividualViewHolder individualViewHolder = (IndividualViewHolder) holder;
        Picasso.get()
                .load("https://insti.app/team-pics/" + individual.getImageName())
                .placeholder(R.drawable.user_placeholder)
                .resize(0, 300)
                .into(individualViewHolder.pictureImageView);
        individualViewHolder.nameTextView.setText(individual.getName());
        individualViewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DeveloperInfo.class);
                intent.putExtra("Image",individual.getImageName());
                intent.putExtra("Name",individual.getName());
                intent.putExtra("Email",individual.getId());
                intent.putExtra("Branch",individual.getBranch());
                context.startActivity(intent);
            }


        });
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
        categoryViewHolder.nameTextView.setText(name);
    }

    private class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_category_about);
        }
    }

    public class IndividualViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private CircleImageView pictureImageView;
        private TextView nameTextView;

        public IndividualViewHolder(@NonNull View itemView) {
            super(itemView);

            rootView = itemView.findViewById(R.id.root_individual_about);
            pictureImageView = itemView.findViewById(R.id.picture_about);
            nameTextView = itemView.findViewById(R.id.name_individual_about);
        }
    }
}
