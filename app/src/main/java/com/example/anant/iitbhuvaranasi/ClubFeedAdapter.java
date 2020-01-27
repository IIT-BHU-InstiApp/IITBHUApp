/*
package com.example.anant.iitbhuvaranasi;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClubFeedAdapter extends RecyclerView.Adapter<ClubFeedAdapter.ClubViewHolder> {

    ArrayList<SingleVerticalData> data = new ArrayList<>();

    public ClubFeedAdapter(ArrayList<SingleVerticalData> data) {
        this.data = data;
    }

    @Override
    public ClubFeedAdapter.ClubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_layout_feedfragment, parent, false);
        return new ClubFeedAdapter.ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClubFeedAdapter.ClubViewHolder holder, int position) {

        holder.image.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getHeader());
        holder.description.setText(data.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ClubViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, description;

        public ClubViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_events);
            title = (TextView) itemView.findViewById(R.id.titles);
            description = (TextView) itemView.findViewById(R.id.);
        }
    }
}
*/
