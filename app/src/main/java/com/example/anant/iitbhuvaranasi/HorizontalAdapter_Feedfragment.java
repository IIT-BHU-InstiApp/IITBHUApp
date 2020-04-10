package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

//import android.util.Log;
//import android.util.Log;

//import static com.example.anant.iitbhuvaranasi.FeedFragment.getHorizontalData;


public class HorizontalAdapter_Feedfragment extends RecyclerView.Adapter<HorizontalAdapter_Feedfragment.MyViewHolder> {

    ArrayList<SingleHorizontaldata> data = new ArrayList<>();
    private Context context;

    public HorizontalAdapter_Feedfragment(Context context,ArrayList<SingleHorizontaldata> data) {
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
//      Log.d("position"," " +position);

        //holder.title.setText(data.get(position).getTitle());
       // Log.d("imageurlhorizontal",data.get(position).getImage());
        Picasso.get()
                .load(data.get(position).getImage())
                .placeholder(R.drawable.ic_cloud_download_black_24dp)
                .error(R.drawable.ic_error_outline_black_24dp)
                .fit()
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

       // Log.d("holderimage",holder.image.toString());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Clubs_group.class);
                intent.putExtra("position",position);
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
        //Log.d("position",""+position);
        //Toast.makeText(context, ""+position, Toast.LENGTH_LONG).show();

    }

    @Override
    public int getItemCount() {
        //Log.d("getitemcount",""+data.size());
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
