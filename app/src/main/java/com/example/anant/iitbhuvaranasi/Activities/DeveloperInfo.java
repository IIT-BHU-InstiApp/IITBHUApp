package com.example.anant.iitbhuvaranasi.Activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;

import android.animation.Animator;

import android.graphics.Rect;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.anant.iitbhuvaranasi.R;
import com.squareup.picasso.Picasso;



public class DeveloperInfo extends AppCompatActivity {
    private Animator mCurrentAnimator;

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;
    private boolean zoomMode;
    private ImageView expandedImageView;
    private Rect startBounds;
    private float startScaleFinal;
    private ImageView userProfilePictureImageView;
    private boolean showingMin = false;
    private String Image, Name, Email, Branch;
    private TextView Name1, Email1, Branch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

//       Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Profile");
//        setSupportActionBar(toolbar);
//       getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Name1 = findViewById(R.id.user_name_profile);
        Email1 = findViewById(R.id.user_email_profile);
        Branch1 = findViewById(R.id.user_rollno_profile);
        Image = getIntent().getStringExtra("Image");
        Name = getIntent().getStringExtra("Name");
        Email = getIntent().getStringExtra("Email");
        Branch = getIntent().getStringExtra("Branch");

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));


        Name1.setText(Name);
        Branch1.setText(Branch);
        Email1.setText(Email);
        userProfilePictureImageView = findViewById(R.id.user_profile_picture_profile);
//        expandedImageView = findViewById(R.id.expanded_image_profile);
        Picasso.get()
                .load("https://insti.app/team-pics/" + Image)
                .placeholder(R.drawable.user_placeholder)
                .resize(0, 300)
                .into(userProfilePictureImageView);



        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

    }



  @Override
    public void onBackPressed() {

            super.onBackPressed();

    }





}
