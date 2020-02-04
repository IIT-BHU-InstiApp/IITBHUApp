package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalRecyclerAdap extends RecyclerView.Adapter<HorizontalRecyclerAdap.HorizontalViewHolder> {
    private Context mContext;
    private int[] mImgId;

    public HorizontalRecyclerAdap(Context context, int[] ImgId){
        mContext = context;
        mImgId = ImgId;
    }

    @NonNull
    @Override
    public HorizontalRecyclerAdap.HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.ciruclar_view, parent, false);
        return new HorizontalViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRecyclerAdap.HorizontalViewHolder holder, int position) {
        holder.img.setImageResource(mImgId[position]);
//        holder.img.setLayoutParams(new ViewGroup.LayoutParams(250,250));
        ViewGroup.LayoutParams params = holder.img.getLayoutParams();
        params.width = 250;
        params.height = 250;
        holder.img.setLayoutParams(params);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "This is a RecyclerView Item", Toast.LENGTH_SHORT).show();
            }
        });
        holder.img.setBorderWidth(2);
        holder.img.setPadding(10,0,10,0);

        holder.img.setElevation(10);

    }

    @Override
    public int getItemCount() {
        return mImgId.length;
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder{
        public CircleImageView img;

        public HorizontalViewHolder(View v){
            super( v);
            img = (CircleImageView) v.findViewById(R.id.circleImage);

        }
    }
}
