package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

        Intent i = getIntent();
        position = i.getIntExtra("position",0);
        Toast.makeText(this,"" + position,Toast.LENGTH_LONG);
        Log.d("whyuio","" + position);

        mExampleList = new ArrayList<>();
        RecyclerView = findViewById(R.id.recyclerView_clubs);
        RecyclerView.setHasFixedSize(true);
        //RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*RecyclerView.setLayoutManager(new GridLayoutManager(this,
       2,
        androidx.recyclerview.widget.RecyclerView.VERTICAL,
        false));*/
       // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
       /* RecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, androidx.recyclerview.widget.RecyclerView.VERTICAL));
*/
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        RecyclerView.setLayoutManager(layoutManager);
      // RecyclerView.setLayoutManager(staggeredGridLayoutManager);
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

