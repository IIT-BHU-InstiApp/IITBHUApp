package com.example.anant.iitbhuvaranasi;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdap extends RecyclerView.Adapter<RecyclerViewAdap.CardViewHolder> {
    private ArrayList<CardViewStruct2> mInfo;
    private Context mContext;

    public RecyclerViewAdap(Context context, ArrayList<CardViewStruct2> info){
        mInfo = info;
        mContext = context;
    }
    @NonNull
    @Override
    public RecyclerViewAdap.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view2, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdap.CardViewHolder holder, int position) {
        CardViewStruct2 cardViewStruct = mInfo.get(position);
        holder.image1.setImageResource(cardViewStruct.getImageid());
        holder.text2.setText(cardViewStruct.getText2());
        holder.text3.setText(cardViewStruct.getText3());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"This is a RecyclerView Item", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mInfo.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        public ImageView image1;
        public TextView text2;
        public TextView text3;

        public CardViewHolder(View view){
            super(view);
            image1 = (ImageView) view.findViewById(R.id.event_image1);
            text2 = (TextView) view.findViewById(R.id.event_title1);
            text3 = (TextView) view.findViewById(R.id.event_dates1);
        }

    }
}

