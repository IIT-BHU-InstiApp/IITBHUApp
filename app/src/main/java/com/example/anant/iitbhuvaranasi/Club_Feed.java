package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.anant.iitbhuvaranasi.FeedFragment.getVerticalData4;

public class Club_Feed extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private TextView club_name;
    private CircleImageView feed_clubimage;
    RecyclerView RecyclerView;
    private LinearLayout custombar;
    SwipeRefreshLayout swipeRefreshLayout;
    private Integer notifid5 = 2;
    String title1;

    public static ArrayList<SingleVerticalData> getVerticalData3;
    public static ArrayList<SingleVerticalData> getVerticalData8 = new ArrayList<>();
    Adapter_CLubFeed adapter_cLubFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club__feed);



        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.club_bar_layout, null);


        club_name = mCustomView.findViewById(R.id.feed_clubname);
        feed_clubimage = mCustomView.findViewById(R.id.feed_clubimage);
        custombar=mCustomView.findViewById(R.id.linear_club1);
      title1= getIntent().getStringExtra("title");
        final String image1 = getIntent().getStringExtra("image");
        Log.d("title123",title1);
        Log.d("image123",image1);
       club_name.setText(title1);
        Picasso.get()
                .load(image1)
                .placeholder(R.drawable.ic_eye_view)
                .error(R.drawable.amc_workshop)
                .into(feed_clubimage);



        custombar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Club_ProfilePage.class);

                i.putExtra("title1",title1);
                i.putExtra("image1",image1);
                startActivity(i);

            }
        });



        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

               // swipeRefreshLayout.setRefreshing(true);

                // Fetching data from server

                Log.d("heloohowareyou2345678","2342567892345");
                loadRecyclerViewData(getVerticalData4);
            }
        });




        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
       // getVerticalData3 = new ArrayList<>();

       /* Log.d("567853", getVerticalData4.toString());

        for(int a=0;a<getVerticalData4.size();a++) {
            Log.d("567853", getVerticalData4.get(a).toString());

            *//*if ( notifid5 <=  Integer.valueOf(getVerticalData1.get(a).getNotifid()))
            {
                notifid5 =  Integer.valueOf(getVerticalData1.get(a).getNotifid());
                Log.d("notifid",notifid5.toString());
            }*//*

            if (getVerticalData4.get(a).getClub_name().equals(title1)){
                getVerticalData3.add(getVerticalData4.get(a));
               // Log.d("567854", getVerticalData3.toString());
              //  Log.d("567853", getVerticalData1.get(a).getClub_name().toString());

            }

         //   Log.d("5678", getVerticalData1.get(a).getClub_name().toString());
        }

        RecyclerView = findViewById(R.id.cub_feed_recyclerview);
        RecyclerView.setHasFixedSize(true);

        RecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        final Adapter_CLubFeed adapter_cLubFeed = new Adapter_CLubFeed(getVerticalData3,Club_Feed.this);
        adapter_cLubFeed.notifyDataSetChanged();
        RecyclerView.setAdapter(adapter_cLubFeed);
        Log.d("vetricalfer",getVerticalData4.toString());*/

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Api_Response.method(getApplicationContext());
                getVerticalData8 = VerticalDataFeed.getVerticalData3(getApplicationContext());
                getVerticalData4 = getVerticalData8;
                Log.d("qwertyu",getVerticalData8.toString());

                loadRecyclerViewData(getVerticalData8);



                /*try {
                    JSONObject response = new JSONObject(response_feed);

                    JSONArray jsonArray = response.getJSONArray("notif");
                    for (int i = jsonArray.length() - 1; i >= 0; i--) {


                        JSONObject hit = jsonArray.getJSONObject(i);
                        Integer notif_id_fetch=hit.getInt("notifid");
                        String notifid=notif_id_fetch.toString();
                        if (notif_id_fetch > notifid5) {

                            // store in share opref hit.toString()
                            // update last post number
                            // read shared pref and add vertical ddata section

                            String club_name = hit.getString("club");
                            String club_image = "http://iitbhuapp.tk" + hit.getString("clubimage");
                            String council_name = hit.getString("council");
                            String council_image = "http://iitbhuapp.tk" + hit.getString("councilimage");
                            String title_event = hit.getString("title");
                            String description_event = hit.getString("description");
                            String image_event = "http://iitbhuapp.tk" + hit.getString("image");
                            String date_event = hit.getString("datetime");
                            String location = hit.getString("location");

                            Integer viewcount1 = hit.getInt("viewedcount");
                            String viewcount = viewcount1.toString();
                            Integer interested1 = hit.getInt("interestedcount");
                            String interestedcount = interested1.toString();
                            String interested = hit.getInt("interested") + "";


                            // Interestedbutton_class.notification_id=notification_id;
                            getVerticalData1.add(new SingleVerticalData(club_name, club_image, council_name, council_image, title_event, description_event
                                    , image_event, date_event, location, viewcount, interestedcount, interested, notifid));
                            Log.d("verticalse00", getVerticalData1.toArray().toString());

                            // Log.d("imageurl00", image);
                            Log.d("verticaldataori00", getVerticalData1.toString());
                        }
                        adapter_cLubFeed.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/



                // implement Handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // cancle the Visual indication of a refresh
                        swipeRefreshLayout.setRefreshing(false);
                        // Generate a random integer number
                        Random r = new Random();
                        int i1 = r.nextInt(80 - 65) + 65;
                        // set the number value in TextView
                        //  textView.setText(String.valueOf(i1));
                    }
                }, 3000);
            }
        });

    }

    @Override
    public void onRefresh() {

        // Fetching data from server
        loadRecyclerViewData(getVerticalData4);
        Log.d("heloohowareyou","23456789");
    }

    private void loadRecyclerViewData(ArrayList<SingleVerticalData> getVerticalData4) {
        getVerticalData3 = new ArrayList<>();


        for(int a=0;a<getVerticalData4.size();a++) {


            if (getVerticalData4.get(a).getClub_name().equals(title1)){
                getVerticalData3.add(getVerticalData4.get(a));
                Log.d("567854", getVerticalData3.toString());
                Log.d("567853", getVerticalData4.get(a).getClub_name().toString());

            }

            Log.d("5678", getVerticalData4.get(a).getClub_name().toString());
        }

        RecyclerView = findViewById(R.id.cub_feed_recyclerview);
        RecyclerView.setHasFixedSize(true);

        RecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

       Adapter_CLubFeed adapter_cLubFeed = new Adapter_CLubFeed(getVerticalData3,Club_Feed.this);
        adapter_cLubFeed.notifyDataSetChanged();
        RecyclerView.setAdapter(adapter_cLubFeed);
        Log.d("vetricalfer",getVerticalData4.toString());
    }




   /* public static ArrayList<SingleVerticalData> getVerticaldata() {
        ArrayList<SingleVerticalData> singleVerticalData = new ArrayList<>();
        singleHorizontals.add(new SingleHorizontaldata(R.drawable.culturalcouncil, "Cultural Council"));
      singleHorizontals.add(new SingleHorizontaldata(R.drawable.saic, "Student Alumni Interaction Cell"));

          return singleVerticalData;
    }*/
   @Override
   public boolean onSupportNavigateUp(){

       finish();
       return true;
   }
}

