package com.example.anant.iitbhuvaranasi;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.VideoView;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    Boolean isInternetPresent = false;
    ConnectionDetector cd;
    private static RequestQueue mRequestQueue;
    // public static ArrayList<SingleVerticalData> getVerticalData1 = new ArrayList<>();
    // public static ArrayList<SingleHorizontaldata>getHorizontalData1=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Splash Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        /**
         * Volley
         */
        cd = new ConnectionDetector(this);
        isInternetPresent = cd.isConnectingToInternet();
        Constants.isInternetPresent = isInternetPresent;
        setContentView(R.layout.activity_main);
        mRequestQueue = Volley.newRequestQueue(this);
        String url = "http://iitbhuapp.tk/feedandclubs";
        JSONObject obj = new JSONObject();
        try {
            obj.put("roll", 18085016);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Api_Response.method(this);
        //getSupportActionBar().hide();

        videoView = (VideoView) findViewById(R.id.videoView);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.animated_logo);
        videoView.setVideoURI(video);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            videoView.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE);
        }

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
//                startActivity(new Intent(MainActivity.this,SignInActivity.class));

                startActivity(new Intent(MainActivity.this,SignInActivity.class));
                finish();
            }
        });

        videoView.start();
    }
}
