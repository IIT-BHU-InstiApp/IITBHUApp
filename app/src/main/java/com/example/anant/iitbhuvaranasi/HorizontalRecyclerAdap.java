package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalRecyclerAdap extends RecyclerView.Adapter<HorizontalRecyclerAdap.HorizontalViewHolder> {
    private Context mContext;
//    private int[] mImgId;
    private ArrayList<String> mImageUrl = new ArrayList<>();
    private ArrayList<String> mTitle = new ArrayList<>();

    public HorizontalRecyclerAdap(Context context, ArrayList<String> ImageUrl, ArrayList<String> Title){
        mContext = context;
        mImageUrl = ImageUrl;
        mTitle = Title;
    }

    @NonNull
    @Override
    public HorizontalRecyclerAdap.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ciruclar_view, parent, false);
        return new HorizontalViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerAdap.HorizontalViewHolder holder, int position) {
//        holder.img.setImageResource();
        final String clubName = mTitle.get(position);
        final String clubImageUrl = mImageUrl.get(position);
        Picasso
                .get()
                .load("http://iitbhuapp.tk" + clubImageUrl)
                .placeholder(R.drawable.background)
                .error(R.drawable.ic_error_outline_black_24dp)
                .noFade()
                .into(holder.img);
//        holder.img.setLayoutParams(new ViewGroup.LayoutParams(250,250));
        float factor = holder.img .getContext().getResources().getDisplayMetrics().density;
        ViewGroup.LayoutParams params = holder.img.getLayoutParams();
        params.width = (int)(95*factor);
        params.height = (int)(95*factor);
        holder.img.setLayoutParams(params);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "This is a RecyclerView Item", Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setBorderWidth(3);
        holder.img.setPadding(8,8,8,0);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,Club_Feed.class);
                intent.putExtra("title",clubName);
                intent.putExtra("image","http://iitbhuapp.tk" + clubImageUrl);
                mContext.startActivity(intent);

            }
        });

        holder.img.setElevation(10);

    }

    @Override
    public int getItemCount() {
        return mImageUrl.size();
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView img;

        public HorizontalViewHolder(View v){
            super( v);
            img = (CircleImageView) v.findViewById(R.id.circleImage);

        }
    }
}
