package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs_group);

        //Added by Suryansh
        Toolbar toolbar = findViewById(R.id.toolbar_club_group);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        mExampleList = new ArrayList<>();
        RecyclerView = findViewById(R.id.recyclerView_clubs);

        //RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.setLayoutManager(new GridLayoutManager(this,
       2,
        androidx.recyclerview.widget.RecyclerView.VERTICAL,
        false));
       // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
       /* RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, androidx.recyclerview.widget.RecyclerView.VERTICAL));
*/
        //layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
      //  RecyclerView.setLayoutManager(layoutManager);
      // RecyclerView.setLayoutManager(staggeredGridLayoutManager);
        Api_Response.method(this, new ServerCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(JSONObject response) {

            }
        });

        SharedPreferences pref2 =  getApplicationContext().getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String response45678 = pref2.getString(Constants.Response_Feed_Old, "2");





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

                Adapter_ClubsGroup adapter_clubsGroup = new Adapter_ClubsGroup(this, mExampleList);
                RecyclerView.setAdapter(adapter_clubsGroup);

            }



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


        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
    @Override
    public boolean onSupportNavigateUp(){
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
        return true;
    }


  /*  @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }*/

    }

