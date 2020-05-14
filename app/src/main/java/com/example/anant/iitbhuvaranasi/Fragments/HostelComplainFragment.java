package com.example.anant.iitbhuvaranasi.Fragments;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.anant.iitbhuvaranasi.Adapters.HostelComplainAdapter;
import com.example.anant.iitbhuvaranasi.Models.Hostel;
import com.example.anant.iitbhuvaranasi.R;

import java.util.ArrayList;

public class HostelComplainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hostelcomplain_fragment, container, false);

        final ArrayList<Hostel> hostels = new ArrayList<>();
        hostels.add(new Hostel("Aryabhatta - I (A & B Block)","https://forms.gle/UvLnpwvGqMndmA9f8"));
        hostels.add(new Hostel("Aryabhatta - II (C & D Block)","https://forms.gle/qSBFJWgVHMB9sMEN7"));
        hostels.add(new Hostel("A.S.N. Bose","https://forms.gle/7xAEgpyxQLKxNukK6"));
        hostels.add(new Hostel("Dhanrajgiri","https://forms.gle/veY7Kuu7Rg5mhHvD9"));
        hostels.add(new Hostel("Dr. C.V. Raman","https://forms.gle/YMLsG553yNXDZDPY9"));
        hostels.add(new Hostel("Dr. S. Ramanujan","https://forms.gle/EevCNkYNRNJ6wrjr9"));
        hostels.add(new Hostel("Gandhi Smriti Chhatravas (EXTENSION)","https://forms.gle/gewWLfdrdLKajHXMA"));
        hostels.add(new Hostel("Gandhi Smriti Chhatravas (OLD)","https://forms.gle/uwp7gJgCiqPhw4xK8"));
        hostels.add(new Hostel("IIT (BHU) Girl's Hostel – I","https://forms.gle/z5jVdYWT2azpzUna9"));
        hostels.add(new Hostel("IIT (BHU) Girl's Hostel – II","https://forms.gle/kgR2yU5wfgGLprKB8"));
        hostels.add(new Hostel("IIT Boy's (Saluja)","https://forms.gle/XKmuzjZhPwBFhPAT8"));
        hostels.add(new Hostel("Limbdi","https://forms.gle/HqBZErxe8ergbh1f8"));
        hostels.add(new Hostel("Morvi","https://forms.gle/Mo11JjNjZDDStMac7"));
        hostels.add(new Hostel("Rajputana","https://forms.gle/q3dFsQYR8GgZrcUS7"));
        hostels.add(new Hostel("S.C. Dey","https://forms.gle/pbioToyMRnZ8PAWC8"));
        hostels.add(new Hostel("Vishwakarma","https://forms.gle/aUWNMdowDDv1Hq5BA"));
        hostels.add(new Hostel("Vishweshvaraiya","https://forms.gle/hGYw9KRFRyRpccuV8"));
        hostels.add(new Hostel("Vivekananda","https://forms.gle/BHLaWjmcouPbm7VQA"));


        HostelComplainAdapter hostelAdapter = new HostelComplainAdapter(getActivity(),hostels);
        ListView hostelComplainListView = (ListView) view.findViewById(R.id.hostelcomplain_listview);
        hostelComplainListView.setAdapter(hostelAdapter);

        hostelComplainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(hostels.get(position).getComplainLink())));

            }
        });


        return view;
    }
}
