package com.example.anant.iitbhuvaranasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PinActivity extends AppCompatActivity {

    private ArrayList<CLubFeedData> mExampleList;
    private ArrayList<String> ClubNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        Api_Response.method(this);

        SharedPreferences pref2 = getApplicationContext().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String response45678 = pref2.getString(Constants.Response_Feed_Old, "2");
        Log.d("response34567890123", response45678);

        try {
            JSONObject jsonObject = new JSONObject(response45678);
            int status = jsonObject.getInt("status");
            JSONArray allcouncils = jsonObject.getJSONArray("councils");
//            JSONObject council = allcouncils.getJSONObject(position);
//            JSONArray clubs = council.getJSONArray("clubs");

            for (int i = 0; i < allcouncils.length(); i++) {
                JSONObject council = allcouncils.getJSONObject(i);
                JSONArray clubs = council.getJSONArray("clubs");
                for (int j = 0; j < clubs.length(); j++) {
                    JSONObject club = clubs.getJSONObject(j);
                    String clubName = club.getString("name");
                    ClubNames.add(clubName);
                }

            }


            Log.d("status0010", Integer.toString(status));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout subList = (LinearLayout) findViewById(R.id.sub_list);

        for (int i = 0; i < ClubNames.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.pin_item, null);
            Switch subItem = (Switch) view.findViewById(R.id.switch_for_pin);
            subItem.setText(ClubNames.get(i));
            subItem.setId(i);

            subList.addView(view);
        }

        final Switch cops = (Switch) subList.findViewById(0);
        final Switch sae = (Switch) subList.findViewById(1);
        final Switch robo = (Switch) subList.findViewById(2);
        final Switch astro = (Switch) subList.findViewById(3);
        final Switch business = (Switch) subList.findViewById(4);
        final Switch amc = (Switch) subList.findViewById(5);
        final Switch csi = (Switch) subList.findViewById(6);
        final Switch kashi = (Switch) subList.findViewById(7);
        final Switch sahyog = (Switch) subList.findViewById(8);
        final Switch dance = (Switch) subList.findViewById(9);

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE);
        cops.setChecked(sharedPrefs.getBoolean("0000", true));

        sae.setChecked(sharedPrefs.getBoolean("0001", true));

        robo.setChecked(sharedPrefs.getBoolean("0002", true));
        astro.setChecked(sharedPrefs.getBoolean("0003", true));
        business.setChecked(sharedPrefs.getBoolean("0004", true));
        amc.setChecked(sharedPrefs.getBoolean("0005", true));
        csi.setChecked(sharedPrefs.getBoolean("0006", true));
        kashi.setChecked(sharedPrefs.getBoolean("0007", true));
        sahyog.setChecked(sharedPrefs.getBoolean("0008", true));
        dance.setChecked(sharedPrefs.getBoolean("0009", true));


        dance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dance.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0009", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0009", false);
                    editor.commit();
                }

            }
        });
        sahyog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sahyog.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0008", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0008", false);
                    editor.commit();
                }

            }
        });
        kashi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kashi.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0007", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0007", false);
                    editor.commit();
                }

            }
        });
        csi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (csi.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0006", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0006", false);
                    editor.commit();
                }

            }
        });
        amc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amc.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0005", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0005", false);
                    editor.commit();
                }

            }
        });
        cops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cops.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0000", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0000", false);
                    editor.commit();
                }

            }
        });
        sae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sae.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0001", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0001", false);
                    editor.commit();
                }

            }
        });
        robo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (robo.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0002", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0002", false);
                    editor.commit();
                }

            }
        });
        astro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (astro.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0003", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0003", false);
                    editor.commit();
                }

            }
        });



        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (business.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0004", true);
                    editor.commit();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
                    editor.putBoolean("0004", false);
                    editor.commit();
                }

            }
        });


//        LinearLayout subList = (LinearLayout) findViewById(R.id.sub_list);
//        String[] clubs = {"Clubs of Programmers",
//                "Society of Automotive Engineers", "Robotics",
//                "Astronomy Club",
//                "Business Club",
//                "Aero Modelling Club",
//                "Club of Sustainability and Innovation",
//                "Kashi Uttkarsh",
//                "Sahyog Club",
//                "Dance Club"};
//        Map<String, int> subListState = new HashMap<>();


//        for(int i = 0; i < clubs.length;i++){
//            View view =  getLayoutInflater().inflate(R.layout.pin_item,null);
//            Switch subItem = (Switch) view.findViewById(R.id.switch_for_pin);
//            subItem.setText(clubs[i]);}
//
//            subList.addView(view);

//


    }

//    @Override
//    public void onClick(View v)
//
//    {
//
//
//        if (cops.isChecked())
//        {
//            SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//            editor.putBoolean("cops", true);
//            editor.commit();
//        }
//        else
//        {
//            SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//            editor.putBoolean("cops", false);
//            editor.commit();
//        }
//
//
//        if (business.isChecked())
//        {
//            SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//            editor.putBoolean("business", true);
//            editor.commit();
//        }
//        else
//        {
//            SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//            editor.putBoolean("business", false);
//            editor.commit();
//        }
//    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);

        finish();

    }
}



