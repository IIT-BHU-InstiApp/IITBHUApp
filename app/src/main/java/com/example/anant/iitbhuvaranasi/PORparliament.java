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
public class PORparliament extends Fragment {


    public PORparliament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_porparliament, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.parliament_contact_container);
        ArrayList<ContactsStruct> arrayList = new ArrayList<>();
        arrayList.add(new ContactsStruct("Vice President", "Harshit Chaudhary","9517270783", "vp.gymkhana@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("AVP", "Harshit Dixit","7992161439", "harshit.dixit.min16@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Anisha Pandey","8368907009", "anishapandey.che18@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Arihant Sukhlecha","8696357434", "arihant.ksukhlecha.cer17@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Avinash Mall","7379040696", "avinash.kmall.civ17@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Mohammed Hussain","9838734090", "mohammed.hussain.cer16@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Muskan Agarwal", "9468866478", "muskan.agarwal.cer16@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Pankaj P N Jarha","8319639968", "pankaj.pnjarha.min16@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Rinish Reddy Vaidyula","9010305713", "vaidyular.reddy.chy15@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Rupali Gharat", "9131214717", "rupali.gharat.cer17@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Vatsal Dwivedi","9571714294", "vatsal.dwivedi.civ17@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor", "Vibhatsu Yadav","8410286425", "vibhatsu.yadav.civ17@itbhu.ac.in"));
        arrayList.add(new ContactsStruct("Convenor)", "VSV Surya Prakash","9441764781", "vsvsurya.prakash.civ16@itbhu.ac.in"));


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
