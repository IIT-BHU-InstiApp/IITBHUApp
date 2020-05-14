package com.example.anant.iitbhuvaranasi.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.anant.iitbhuvaranasi.Adapters.Adapter_CLubFeed;
import com.example.anant.iitbhuvaranasi.BackendResponse.Api_Response;
import com.example.anant.iitbhuvaranasi.Models.SingleVerticalData;
import com.example.anant.iitbhuvaranasi.R;
import com.example.anant.iitbhuvaranasi.Interfaces.ServerCallback;
import com.example.anant.iitbhuvaranasi.BackendResponse.VerticalDataFeed;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.anant.iitbhuvaranasi.Fragments.FeedFragment.getVerticalData4;

public class Club_Feed extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private TextView club_name;
    private CircleImageView feed_clubimage;
    ImageButton about_button;
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

        swipeRefreshLayout =  findViewById(R.id.swiperefreshlayout);
        Toolbar toolbar = findViewById(R.id.club_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//

        club_name = toolbar.findViewById(R.id.feed_clubname);
        feed_clubimage = toolbar.findViewById(R.id.feed_clubimage);
        about_button = toolbar.findViewById(R.id.about_club);

        custombar=toolbar.findViewById(R.id.linear_club1);
        title1= getIntent().getStringExtra("title");
        final String image1 = getIntent().getStringExtra("image");
//
//
        club_name.setText(title1);
       /* Picasso.get()
                .load(image1)
                .placeholder(R.drawable.thumb_drawable)
                .error(R.drawable.thumb_drawable)
                .into(feed_clubimage);*/
        Glide.with(this)
                .load(image1)
                .error(R.drawable.background)
                .thumbnail(.1f)
                .fitCenter() // scale to fit entire image within ImageView
                .into(feed_clubimage);



        about_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Club_ProfilePage.class);

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


                loadRecyclerViewData(getVerticalData4);
            }
        });







        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Api_Response.method(getApplicationContext(), new ServerCallback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
                getVerticalData8 = VerticalDataFeed.getVerticalData3(getApplicationContext());
                getVerticalData4 = getVerticalData8;


                loadRecyclerViewData(getVerticalData8);
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
    }

    private void loadRecyclerViewData(ArrayList<SingleVerticalData> getVerticalData4) {
        getVerticalData3 = new ArrayList<>();


        for(int a=0;a<getVerticalData4.size();a++) {


            if (getVerticalData4.get(a).getClub_name().equals(title1)){
                getVerticalData3.add(getVerticalData4.get(a));

            }

        }

        RecyclerView = findViewById(R.id.cub_feed_recyclerview);
        RecyclerView.setHasFixedSize(true);

        RecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Adapter_CLubFeed adapter_cLubFeed = new Adapter_CLubFeed(getVerticalData3,Club_Feed.this);
        adapter_cLubFeed.notifyDataSetChanged();
        RecyclerView.setAdapter(adapter_cLubFeed);
      }
   @Override
    public boolean onSupportNavigateUp(){

        finish();
        return true;
    }
}

