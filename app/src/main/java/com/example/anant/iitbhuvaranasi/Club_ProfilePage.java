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


    }
}
