//package com.example.anant.iitbhuvaranasi.Fragments;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.anant.iitbhuvaranasi.Adapters.VerticalAdapter_Feedfragment;
//import com.example.anant.iitbhuvaranasi.Models.SingleVerticalData;
//import com.example.anant.iitbhuvaranasi.R;
//import com.example.anant.iitbhuvaranasi.BackendResponse.VerticalDataFeed;
//
//import java.util.ArrayList;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class UpcomingEventsFragment extends Fragment {
//    ArrayList<SingleVerticalData> mUpComingEvents;
//
//    public UpcomingEventsFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        mUpComingEvents = VerticalDataFeed.getUpcomingEvents(getContext());
//
//        View view = inflater.inflate(R.layout.fragment_upcoming_events, container, false);
//        RecyclerView recyclerView = view.findViewById(R.id.upcoming_events);
//        RecyclerView.Adapter adapter = new VerticalAdapter_Feedfragment(getContext(),mUpComingEvents);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        return view;
//    }
//}
