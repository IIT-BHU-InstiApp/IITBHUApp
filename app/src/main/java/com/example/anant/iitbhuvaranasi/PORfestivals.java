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
public class PORfestivals extends Fragment {


    public PORfestivals() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_porfestivals, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.festiv_contact_container);
        ArrayList<ContactsStruct> arrayList = new ArrayList<>();
        arrayList.add(new ContactsStruct("Convenor(Kashiyatra)", "Utsav Gandhi", "9407273803", "kashiyatra@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor(Technex)", "Tanmay agarwal", "8017394890", "technex@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor(Spardha)", "Saksham Maheshwari", "9782524351", "convener.spardha@iitbhu.ac.in"));

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

            email.setTextColor(getResources().getColor(R.color.colorblue200));
            number.setTextColor(getResources().getColor(R.color.colorblue200));

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
