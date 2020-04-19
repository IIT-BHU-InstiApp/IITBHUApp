package com.example.anant.iitbhuvaranasi;

import android.content.Context;
import android.content.SharedPreferences;

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




       // Toast.makeText(context, "Hello" + (viewType+4), Toast.LENGTH_LONG).show();

       // viewType = viewType plus1;

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


        if (position == 0 || position == 1) {










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
       //

        HorizontalAdapter_Feedfragment adapter = new HorizontalAdapter_Feedfragment(context, getHorizontalData1);

     //
        //holder.recyclerView.setLayoutManager(new LinearLayoutManager(context3));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        //holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
       // holder.recyclerView.setLayoutManager(new GridLayoutManager(context3, 2, GridLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        //holder.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, VERTICAL));

    }

    private void verticalView(VerticalViewHolder holder) {

        VerticalAdapter_Feedfragment adapter = new VerticalAdapter_Feedfragment(context, getVerticalData5);


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
