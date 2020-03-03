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
public class ContactsAcademics extends Fragment {


    public ContactsAcademics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts_academics, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.academics_container);
        ArrayList<ContactsStruct> arrayList = new ArrayList<>();
        arrayList.add(new ContactsStruct("Director", "Prof. Pramod Kumar Jain","+91 542 2368106","director@iitbhu.ac.in" ));
        arrayList.add(new ContactsStruct("Dean (Academic Affairs)", "Prof. Shyam Bihari Dwivedi","9792262852","doaa@iitbhu.ac.in" ));
        arrayList.add(new ContactsStruct("Dean (Students' Affairs)","Prof. B. N. Rai","9450533441","dosa@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("Dean (Research & Development)","Prof. Rajiv Prakash","9935033011","dord@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("Dean (Faculty Affairs)","Prof. K. K. Shukla","NA","dofa@iitbhu.ac.in"));
        arrayList.add(new ContactsStruct("Dean (Resource & Alumni)","Prof. Anil Kumar Tripathi","+91 542 6702756","dora@iitbhu.ac.in"));
        for(int i = 0; i<arrayList.size();i++){
            View contact_view = LayoutInflater.from(getContext()).inflate(R.layout.contact_view,linearLayout,false);
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
