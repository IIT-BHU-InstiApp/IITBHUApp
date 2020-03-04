package com.example.anant.iitbhuvaranasi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HostelComplainAdapter extends ArrayAdapter<Hostel> {

        public HostelComplainAdapter(Activity context, ArrayList<Hostel> hostel){

            super(context,0, hostel);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.hostelcomplain_listview_layout, parent, false);
        }

        final Hostel currentHostel = getItem(position);

        TextView hostelName = listItemView.findViewById(R.id.hoste_name);
        hostelName.setText(currentHostel.getHostelName());

        return listItemView;
    }
}
