package com.example.anant.iitbhuvaranasi.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.anant.iitbhuvaranasi.Models.ContactsStruct;
import com.example.anant.iitbhuvaranasi.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsSecurity extends Fragment {


    public ContactsSecurity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts_security, container, false);

        LinearLayout linearLayout = view.findViewById(R.id.security_contact_container);
        ArrayList<ContactsStruct> arrayList = new ArrayList<>();
        arrayList.add(new ContactsStruct("Health Emergency", "Sir Sunder Lal Hospital", "0542-2369844", ""));
        arrayList.add(new ContactsStruct("Health Emergency", "Sir Sunder Lal Hospital", "0542-2309308", ""));

        arrayList.add(new ContactsStruct("Dy. Chief Proctor", "Prof. Prabhakar Singh", "8004924507", ""));
        arrayList.add(new ContactsStruct("Proctor", "Prof. Rajendra Prasad", "9453048438", ""));
        arrayList.add(new ContactsStruct("Security Officer", "Mr. Shashank Shekhar Prasad Singh", "8004925734", ""));
        arrayList.add(new ContactsStruct("Proctor Office, IIT(BHU)", "", "0542-2366744", "office.proctor@iitbhu.ac.in"));

        ///DONT TRY TO UNDERSTANT THIS, KUCH SAMAJH NAHI AAYEGA.(VARIABLE K NAAM GALAT H).
        for (int i = 0; i < arrayList.size(); i++) {
            View contact_view = LayoutInflater.from(getContext()).inflate(R.layout.contact_view, linearLayout, false);
            TextView post = contact_view.findViewById(R.id.post_contact);
            TextView name = contact_view.findViewById(R.id.name_contact);

            final TextView number = contact_view.findViewById(R.id.email_contact);

            if(i<2){
                post.setText(arrayList.get(i).getPost());
                name.setText(arrayList.get(i).getName());
                number.setText(arrayList.get(i).getNumber());
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
            }
            else if(i>=2 && i<5){
                post.setText(arrayList.get(i).getPost());
                name.setText(arrayList.get(i).getName());
                number.setText(arrayList.get(i).getNumber());
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
            }
            else{
                post.setText(arrayList.get(i).getPost());
                name.setText(arrayList.get(i).getNumber());
                number.setText(arrayList.get(i).getEmail());
                number.setTextColor(getResources().getColor(R.color.colorblue220));
                name.setTextColor(getResources().getColor(R.color.colorblue220));
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:" + number.getText()));
                        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                            startActivity(intent);
                        }
                    }
                });
                number.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + number.getText()));
                        startActivity(Intent.createChooser(emailIntent, "email"));
                    }
                });
            }








            linearLayout.addView(contact_view);
        }
        return view;
    }

}
