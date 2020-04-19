package com.example.anant.iitbhuvaranasi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSubscribedNotification extends RecyclerView.Adapter<AdapterSubscribedNotification.NotificationViewHolder> {

    private ArrayList<SubscribedNotification> notifications ;

    public AdapterSubscribedNotification (ArrayList<SubscribedNotification> notifications){
        this.notifications = notifications;

    }

    @NonNull
    @Override
    public AdapterSubscribedNotification.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subscribed_notification,null,false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSubscribedNotification.NotificationViewHolder holder, int position) {
        holder.mImageView.setImageResource(notifications.get(position).getImageId());
        holder.mTextView.setText(""+notifications.get(position).getNoOfNotifications());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_field);
            mTextView = itemView.findViewById(R.id.no_of_notifiocations);

        }
    }
}

