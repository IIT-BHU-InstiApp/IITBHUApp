package com.example.anant.iitbhuvaranasi.Adapters;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anant.iitbhuvaranasi.Activities.Clubs_group;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltAllCouncilsPost;
import com.example.anant.iitbhuvaranasi.R;
import com.example.anant.iitbhuvaranasi.Models.SingleHorizontaldata;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Tag;

import static app.AppController.TAG;


public class HorizontalAdapter_Feedfragment extends RecyclerView.Adapter<HorizontalAdapter_Feedfragment.MyViewHolder> {

    List<BuiltAllCouncilsPost> data;
    private Context context;

    public HorizontalAdapter_Feedfragment(Context context, List<BuiltAllCouncilsPost> data) {
        this.data = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_layout_feedfragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//

        //holder.title.setText(data.get(position).getTitle());
       //
        Glide.with(context)
                .load(data.get(position).getSmall_image_url())
                .error(R.drawable.background)
                .thumbnail(0.1f)
                .into(holder.image);
//        ViewGroup.LayoutParams params = holder.image.getLayoutParams();
//        params.width = 250;
//        params.height = 250;
//        holder.image.setLayoutParams(params);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This is a RecyclerView Item", Toast.LENGTH_SHORT).show();
            }
        });
//        holder.image.setBorderWidth(2);
        holder.image.setPadding(10,0,10,0);

        holder.image.setElevation(10);

       //

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Clubs_group.class);
//                intent.putExtra("position",position);
                intent.putExtra("id",data.get(position).getId());
                context.startActivity(intent);
            }
        });
       /* if(data==getHorizontalData()){
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Clubs_group.class);
                intent.putExtra("image", data.get(position).getImage());
                context.startActivity(intent);
            }
        });
        }

        else{
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ClubFeed.class);
                    intent.putExtra("image", data.get(position).getImage());
                    context.startActivity(intent);
                }
            });
        }*/
        //holder.title.setText(data.get(position).getTitle());
        //Picasso.get().load(data.get(position).getImage()).into(holder.images);
        //Toast.makeText(FeedFragment.class, ""+position, Toast.LENGTH_SHORT).show();
        //
        //Toast.makeText(context, ""+position, Toast.LENGTH_LONG).show();

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView title;
        ImageView image;

       // LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            //title = (TextView) itemView.findViewById(R.id.council_title);
            image =  itemView.findViewById(R.id.council_image);
            //linearLayout=itemView.findViewById(R.id.linearlayout_feedfragment);
        }
    }

}
