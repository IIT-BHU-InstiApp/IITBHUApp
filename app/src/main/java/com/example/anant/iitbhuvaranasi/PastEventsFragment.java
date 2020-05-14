package com.example.anant.iitbhuvaranasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PastEventsFragment extends Fragment {
    ArrayList<SingleVerticalData> mPastEvents;
    public PastEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mPastEvents = VerticalDataFeed.getPastEvents(getContext());

        View view = inflater.inflate(R.layout.fragment_past_events, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.past_events);
        RecyclerView.Adapter adapter = new VerticalAdapter_Feedfragment(getContext(),mPastEvents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
