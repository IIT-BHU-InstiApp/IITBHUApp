package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_ClubsGroup extends RecyclerView.Adapter<Adapter_ClubsGroup.ViewHolder> {

    private Context mContext;
    private ArrayList<CLubFeedData> mExampleList;

    public Adapter_ClubsGroup(Context mContext, ArrayList<CLubFeedData> mExampleList) {
        this.mContext = mContext;
        this.mExampleList = mExampleList;
    }

    @NonNull
    @Override
    public Adapter_ClubsGroup.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.clubs_recylerview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ClubsGroup.ViewHolder holder, int position) {

        holder.mTitle.setText(mExampleList.get(position).getTitle());
        final String title1 = mExampleList.get(position).getTitle();
        final String image_url = mExampleList.get(position).getImage();
        Picasso.get()
                .load(mExampleList.get(position).getImage())
                .placeholder(R.drawable.ic_cloud_download_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .into(holder.image);
        // Title appears disabled if item is disabled

        holder.club_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Club_Feed.class);
                intent.putExtra("title",title1);
                intent.putExtra("image",image_url);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTitle;
        public ImageView image;
        public LinearLayout club_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.title1);
            club_card = itemView.findViewById(R.id.club_card);
            image = (ImageView) itemView.findViewById(R.id.club_image);
        }
    }
}