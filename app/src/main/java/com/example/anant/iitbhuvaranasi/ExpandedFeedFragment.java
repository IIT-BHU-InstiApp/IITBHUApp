package com.example.anant.iitbhuvaranasi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandedFeedFragment extends Fragment {


    public ExpandedFeedFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expanded_feed, container, false);
        ViewPager viewPager = view.findViewById(R.id.expandedfeed_viewpager);
        viewPager.setAdapter(new ExpandedFeedAdapter(getChildFragmentManager()));
        TabLayout tabLayout = view.findViewById(R.id.tab_layout_expanded_feed);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
