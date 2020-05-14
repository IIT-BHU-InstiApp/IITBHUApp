package com.example.anant.iitbhuvaranasi.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anant.iitbhuvaranasi.Adapters.PORadapter;
import com.example.anant.iitbhuvaranasi.R;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsPOR extends Fragment {


    public ContactsPOR() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts_por, container, false);
        ViewPager viewPager = view.findViewById(R.id.por_viewpager);
        PORadapter poRadapter = new PORadapter(getChildFragmentManager()); ///////////////////////////////////////////////
        viewPager.setAdapter(poRadapter);
        TabLayout tabLayout = view.findViewById(R.id.por_tab);
        tabLayout.setupWithViewPager(viewPager);
        return view;


    }

}
