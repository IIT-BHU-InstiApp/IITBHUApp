package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Clubs_group extends AppCompatActivity {

    RecyclerView RecyclerView;
    private ArrayList<CLubFeedData> mExampleList;
    Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_group);

        //Added by Suryansh
        Toolbar toolbar = findViewById(R.id.toolbar_club_group);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));

        //Added by Suryansh

        Intent i = getIntent();
        position = i.getIntExtra("position",0);
        Toast.makeText(this,"" + position,Toast.LENGTH_LONG);
        Log.d("whyuio","" + position);

        mExampleList = new ArrayList<>();
        RecyclerView = findViewById(R.id.recyclerView_clubs);
        RecyclerView.setHasFixedSize(true);
        //RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.setLayoutManager(new GridLayoutManager(this,
       2,
        androidx.recyclerview.widget.RecyclerView.VERTICAL,
        false));

        Api_Response.method(this);

        SharedPreferences pref2 =  getApplicationContext().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String response45678 = pref2.getString(Constants.Response_Feed_Old, "2");
        Log.d("response34567890123",response45678);




        try {
            JSONObject jsonObject = new JSONObject(response45678);
            int status = jsonObject.getInt("status");
            JSONArray allcouncils= jsonObject.getJSONArray("councils");
            JSONObject council = allcouncils.getJSONObject(position);
            JSONArray clubs = council.getJSONArray("clubs");
            for(int a=0;a<clubs.length();a++)
            {
                JSONObject club = clubs.getJSONObject(a);
                String club_name = club.getString("name");
                String image_club = "http://iitbhuapp.tk" + club.getString("image");
                mExampleList.add(new CLubFeedData(image_club, club_name));
                Log.d("status04560",club_name );
                Adapter_ClubsGroup adapter_clubsGroup = new Adapter_ClubsGroup(this, mExampleList);
                RecyclerView.setAdapter(adapter_clubsGroup);

            }

            Log.d("status0010", Integer.toString(status));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*try {
            JSONObject jsonObject = new JSONObject(response);
            int status = jsonObject.getInt("status");
            JSONArray allcouncils= jsonObject.getJSONArray("councils");
            JSONObject council = allcouncils.getJSONObject(position);
            JSONArray clubs = council.getJSONArray("clubs");
            for(int a=0;a<clubs.length();a++)
            {
                JSONObject club = clubs.getJSONObject(a);
                String club_name = club.getString("name");
                String image_club = club.getString("image");
            }

            Log.d("status0010", Integer.toString(status));
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }




    }

