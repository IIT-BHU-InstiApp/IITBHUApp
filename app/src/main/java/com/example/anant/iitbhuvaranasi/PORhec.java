package com.example.anant.iitbhuvaranasi;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PORhec extends Fragment {


    public PORhec() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_porhec, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.hec_contact_container);
        ArrayList<ContactsStruct> arrayList = new ArrayList<>();
        arrayList.add(new ContactsStruct("General Secretary(Science & Technology Council)", "Amrtanshu Raj", "7376203816", "amrtanshu.raj.eee16@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("General Secretary (Cultural Council)", "Apoorv Bansal", "9456955785", "apoorv.bansal.mat16@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("General Secretary (Games and Sports Council)", "Deep Chauhan", "7237874633", "deep.chauhan.mec15@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("General Secretary (Film and Media Council)", "Pranav Raibagkar", "9130823988", "rpranav.pravin.phy16@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("General Secretary (Social Service Council)", "Varnit Sharan", "7007234375", "varnit.sharan.phe16@itbhu.ac.in"));
        for (int i = 0; i < arrayList.size(); i++) {
            View contact_view = LayoutInflater.from(getContext()).inflate(R.layout.contact_view, linearLayout, false);
            TextView post = contact_view.findViewById(R.id.post_contact);
            TextView name = contact_view.findViewById(R.id.name_contact);
            final TextView number = contact_view.findViewById(R.id.number_contact);
            final TextView email = contact_view.findViewById(R.id.email_contact);
            post.setText(arrayList.get(i).getPost());
            name.setText(arrayList.get(i).getName());
            number.setText(arrayList.get(i).getNumber());
            email.setText(arrayList.get(i).getEmail());

            email.setTextColor(getResources().getColor(R.color.colorblue220));
            number.setTextColor(getResources().getColor(R.color.colorblue220));

            number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + number.getText()));
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email.getText()));
                    startActivity(Intent.createChooser(emailIntent, "email"));
                }
            });


            linearLayout.addView(contact_view);
        }
        return view;
    }

}
