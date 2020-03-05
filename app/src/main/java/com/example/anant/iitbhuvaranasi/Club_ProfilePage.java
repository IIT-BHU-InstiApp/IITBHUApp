package com.example.anant.iitbhuvaranasi;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Club_ProfilePage extends AppCompatActivity {

    private TextView club_name1;
    private CircleImageView feed_clubimage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club__profile_page);

        //Added by Suryansh
        Toolbar toolbar = findViewById(R.id.toolbar_profile_page);

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

        club_name1 = findViewById(R.id.club_profile_name);
        feed_clubimage1 = findViewById(R.id.club_profile_image);
        final String title1= getIntent().getStringExtra("title1");
        final String image1 = getIntent().getStringExtra("image1");
        club_name1.setText(title1);
        Picasso.get()
                .load(image1)
                .placeholder(R.drawable.ic_eye_view)
                .error(R.drawable.amc_workshop)
                .into(feed_clubimage1);
        TextView ClubInfo = findViewById(R.id.club_info);
        if(title1.contentEquals("Clubs Of Programmers")){ClubInfo.setText(getResources().getString(R.string.cops));}
        else if(title1.contentEquals("Society Of Automotive Engineers")){ClubInfo.setText(getResources().getString(R.string.sae));}
        else if(title1.contentEquals("Robotics")){ClubInfo.setText(getResources().getString(R.string.robotics));}
        else if(title1.contentEquals("Astronomy Club")){ClubInfo.setText(getResources().getString(R.string.astro));}
        else if(title1.contentEquals("Business Club")){ClubInfo.setText(getResources().getString(R.string.business));}
        else if(title1.contentEquals("Aero Modelling Club")){ClubInfo.setText(getResources().getString(R.string.amc));}
        else if(title1.contentEquals("Club of Sustainablity and Innovation")){ClubInfo.setText(getResources().getString(R.string.csi));}
        else if(title1.contentEquals("Kashi Uttkarsh")){ClubInfo.setText(getResources().getString(R.string.kashi));}
        else if(title1.contentEquals("Sahyog Club")){ClubInfo.setText(getResources().getString(R.string.sahyog));}
        else if(title1.contentEquals("Dance Club")){ClubInfo.setText(getResources().getString(R.string.dance));}
        else{
            ClubInfo.setText("No information about the club");
        }





    }
}
