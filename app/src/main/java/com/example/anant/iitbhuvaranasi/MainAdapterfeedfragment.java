package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.anant.iitbhuvaranasi.FeedFragment.getHorizontalData1;
import static com.example.anant.iitbhuvaranasi.FeedFragment.getVerticalData5;


public class MainAdapterfeedfragment extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Object> items;
    private final int HORIZONTAL = 0;
    private final int VERTICAL = 1;
    SharedPreferences sharedPreferences;
    Integer i=0;


    public MainAdapterfeedfragment(Context context, ArrayList<Object> items) {
        this.context = context;
        this.items = items;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder holder;
        Log.d("0987654",Interestedbutton_class.getNotification_id().toString());

        Log.d("09876545",Interestedbutton_class.getNotification_id().toString());

       // Toast.makeText(context, "Hello" + (viewType+4), Toast.LENGTH_LONG).show();
        Log.d("viewtypemainadapter"," "+ viewType);
       // viewType = viewType +1;

        switch (viewType) {
            case VERTICAL:
                view = inflater.inflate(R.layout.vertical_recycler_feedfragment, parent, false);
                holder = new VerticalViewHolder(view);
                break;

            case HORIZONTAL:
                view = inflater.inflate(R.layout.horizontal_recycler_feedfragment, parent, false);
                holder = new HorizontalViewHolder(view);
                break;

            default:
                view = inflater.inflate(R.layout.vertical_recycler_feedfragment, parent, false);
                holder = new VerticalViewHolder(view);
                viewType = viewType +1;
                break;
        }
             return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       /* Toast.makeText(context, "Hello"+position, Toast.LENGTH_LONG).show();*/
        Log.d("positionmainadapter"," "+ position);

        if (position == 0 || position == 1) {
            Log.d("positionmainadapter01"," "+holder.getItemViewType());
            Log.d("positionmainadapter02"," "+holder.getPosition());
            Log.d("positionmainadapter03"," "+holder.getLayoutPosition());
            Log.d("positionmainadapter04"," "+holder.getAdapterPosition());
            Log.d("positionmainadapter05"," "+holder.getItemId());
            Log.d("positionmainadapter06"," "+holder.getOldPosition());

            Log.d("098765432",Interestedbutton_class.getNotification_id().toString());


            switch (holder.getAdapterPosition()) {

                case VERTICAL:
                    verticalView((VerticalViewHolder) holder);
                    break;
                case HORIZONTAL:
                    horizontalView((HorizontalViewHolder) holder);
                    break;
                default:
                    verticalView((VerticalViewHolder) holder);
                    break;
            }
        }


        /*if (holder.getItemViewType() == HORIZONTAL)
            horizontalView((HorizontalViewHolder) holder);
        else if (holder.getItemViewType() == VERTICAL)
            verticalView((VerticalViewHolder) holder);*/
    }



    private void horizontalView(HorizontalViewHolder holder) {
        //Toast.makeText(context, "Hello" + holder, Toast.LENGTH_LONG).show();
       // Log.d("holder", "" + holder);
        Log.d("1000","mainadapterhorizontal0");
        HorizontalAdapter_Feedfragment adapter = new HorizontalAdapter_Feedfragment(context, getHorizontalData1);
        Log.d("1001","mainadapterhorizontal");
     // Log.d("horizontaldataadapter",getHorizontalData().toString());
        //holder.recyclerView.setLayoutManager(new LinearLayoutManager(context3));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        //holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
       // holder.recyclerView.setLayoutManager(new GridLayoutManager(context3, 2, GridLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        //holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));

    }

    private void verticalView(VerticalViewHolder holder) {
        Log.d("1002","mainadaptervertical0");
        VerticalAdapter_Feedfragment adapter = new VerticalAdapter_Feedfragment(context, getVerticalData5);
       //Log.d("verticaladapter",getVerticalData().toString());
        Log.d("1003","mainadaptervertical");
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapter);
    }



    @Override
    public int getItemCount() {
        return items.size();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
//        if (items.get(position) instanceof SingleHorizontaldata)
//            return HORIZONTAL;
//       else if (items.get(position) instanceof SingleVerticalData)
//            return VERTICAL;
//       return -1;

       /* if (position == 0) {


            if (items.get(position) instanceof SingleVerticalData)
                return VERTICAL;
            if (items.get(position) instanceof SingleHorizontaldata)
                return HORIZONTAL;
        }
        return -1;*/

        /*switch(position) {

            case 0:
            if (items.get(position) instanceof SingleHorizontaldata) {
                return HORIZONTAL;

            }
            else
                return VERTICAL;

            *//*if (items.get(position) instanceof SingleVerticalData)
                return VERTICAL;*//*


         *//* case 1:
                if (items.get(position) instanceof SingleHorizontaldata)
                    return HORIZONTAL;
                if (items.get(position) instanceof SingleVerticalData)
                    return VERTICAL;

            break;*//*
        }
        return -1;*/
    }


    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        HorizontalViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recyclerView);
        }
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        VerticalViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recyclerView);
        }
    }

}
