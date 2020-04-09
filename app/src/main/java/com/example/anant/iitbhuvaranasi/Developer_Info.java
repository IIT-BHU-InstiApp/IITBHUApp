/*
package com.example.anant.iitbhuvaranasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Developer_Info extends AppCompatActivity {

    private String Image, Name, Email, Branch;
    private TextView Name1, Email1, Branch1;
    private ImageView userProfilePictureImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer__info);

        Name1 = findViewById(R.id.user_name_profile);
        Email1 = findViewById(R.id.user_email_profile);
        Branch1 = findViewById(R.id.user_rollno_profile);
        Image = getIntent().getStringExtra("Image");
        Name = getIntent().getStringExtra("Name");
        Email = getIntent().getStringExtra("Email");
        Branch = getIntent().getStringExtra("Branch");

        Name1.setText(Name);
        Branch1.setText(Branch);
        Email1.setText(Email);
        userProfilePictureImageView = findViewById(R.id.user_profile_picture_profile);
       // expandedImageView = findViewById(R.id.expanded_image_profile);
        Picasso.get()
                .load("https://insti.app/team-pics/" + Image)
                .placeholder(R.drawable.user_placeholder)
                .resize(0, 300)
                .into(userProfilePictureImageView);
    }
}
*/
