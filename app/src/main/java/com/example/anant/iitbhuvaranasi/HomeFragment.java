package com.example.anant.iitbhuvaranasi;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public static ArrayList<SingleHorizontaldata>getHorizontalData1=new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View home =  inflater.inflate(R.layout.fragment_home, container, false);
        //images for flipper
       int images[] = {R.drawable.sntc_astro, R.drawable.sntc_cops,R.drawable.sntc_aero, R.drawable.sntc_robotics, R.drawable.sntc_business,  R.drawable.sntc_auto, R.drawable.ecell,R.drawable.sntc_sustainablity};


       //API
        getHorizontalData1=new ArrayList<>();

        SharedPreferences pref3 = this.getActivity().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String resonse_feed = pref3.getString(Constants.Response_Feed_Old,"3");

        String url = "http://iitbhuapp.tk/feedandclubs";

        /**
         * Fetching Data from sharedpref
         */
        try {
            JSONObject response = new JSONObject(resonse_feed);
            int status = response.getInt("status");
            Log.d("status001", Integer.toString(status));

            if (status == 1) {
                Log.d("status100", "1");
                JSONArray jsonArray = response.getJSONArray("notif");
                JSONArray array = response.getJSONArray("councils");


                for (int j = 0; j < array.length(); j++){
                    JSONObject hit1 = array.getJSONObject(j);
                    String image_council = "http://iitbhuapp.tk" + hit1.getString("image");
                    //  Log.d("clubname", name);
                    //Log.d("imageurl",image_council);

                    getHorizontalData1.add(new SingleHorizontaldata(image_council));
                }
                Log.d("horizontaldata234500", getHorizontalData1.toString());


            }
            else {
                Log.d("status000", "0");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }




        // API

        //ViewPager
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) home.findViewById(R.id.viewpager);
        DotsIndicator dotsIndicator = (DotsIndicator) home.findViewById(R.id.dots_indicator);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleViewAdapter adapter = new SimpleViewAdapter(getContext(), getHorizontalData1);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        //RECYCLERVIEW VERTICAL
        ArrayList<CardViewStruct2> cardViewStructs = new ArrayList<CardViewStruct2>();
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide1, "Technex Workshop", "31-Jan-2020, 06:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide2, "COPS ML Workshop", "29-Jan-2020, 10:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide3, "TicTacToe", "20-Jan-2020, 17:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide4, "IntraFreshers Cricket Tournament", "11-Jan-2020, 00:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide5, "Neural Networks", "15-Jan-2020, 12:05 Hrs"));


        RecyclerView rcv = (RecyclerView) home.findViewById(R.id.rcv);
        RecyclerViewAdap recyclerViewAdap = new RecyclerViewAdap(getContext(), cardViewStructs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(recyclerViewAdap);

        //RECYCLERVIEW HORIZONTAL
        int[] imgId = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5};
        RecyclerView horizontalRcv = (RecyclerView) home.findViewById(R.id.horizontal_rcv);
        HorizontalRecyclerAdap horizontalRecyclerAdap = new HorizontalRecyclerAdap(getContext(), imgId);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRcv.setLayoutManager(layoutManager2);
        horizontalRcv.setAdapter(horizontalRecyclerAdap);

        return home;
    }

}
