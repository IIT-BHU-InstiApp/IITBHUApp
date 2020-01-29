package com.example.anant.iitbhuvaranasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main2Activity extends AppCompatActivity {
//    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //images for flipper
        int images[] = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3,R.drawable.slide4,R.drawable.slide5};

        LinearLayout pin = (LinearLayout) findViewById(R.id.pin);

        //configuring flipper
//        flipper = findViewById(R.id.flipper);
//        for(int i = 0; i < images.length; i++){
//            addViewFlip(images[i]);
//        }
        for(int i = 0; i < images.length; i++){
            addViewHorizontal(pin,images[i]);
        }
//        confiureFlipper(flipper);

        //ViewPager
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        DotsIndicator dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);

        // Create an adapter that knows which fragment should be shown on each page
        SimpleViewAdapter adapter = new SimpleViewAdapter(this, images);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        dotsIndicator.setViewPager(viewPager);

        //RECYCLERVIEW
        ArrayList<CardViewStruct2> cardViewStructs= new ArrayList<CardViewStruct2>();
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide1,"Technex Workshop","31-Jan-2020, 06:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide2,"COPS ML Workshop","29-Jan-2020, 10:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide3,"TicTacToe","20-Jan-2020, 17:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide4,"IntraFreshers Cricket Tournament","11-Jan-2020, 00:05 Hrs"));
        cardViewStructs.add(new CardViewStruct2(R.drawable.slide5,"Neural Networks","15-Jan-2020, 12:05 Hrs"));


        RecyclerView rcv = (RecyclerView) findViewById(R.id.rcv);
        RecyclerViewAdap recyclerViewAdap = new RecyclerViewAdap(this,cardViewStructs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(recyclerViewAdap);

        //Added by Suryansh.








        //Added by Suryansh.




    }


//    public void confiureFlipper(ViewFlipper flipper){
//        flipper.setFlipInterval(4000);
//        flipper.setAutoStart(true);
//        flipper.setInAnimation(this, R.anim.slideinright);
//        flipper.setOutAnimation(this, R.anim.slideoutleft);
//        flipper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"fuck",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    //    public void addViewFlip(int img){
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource(img);
//
//        flipper.addView(imageView);
//
//    }
    public void addViewHorizontal(LinearLayout pin,int img){

        CircleImageView circularImageView =  new CircleImageView(this);
        circularImageView.setLayoutParams(new ViewGroup.LayoutParams(250,250));
        circularImageView.setImageResource(img);
        circularImageView.setBorderWidth(2);
        circularImageView.setPadding(10,0,10,0);
        circularImageView.setBorderColor(getResources().getColor(R.color.colorblack));
        circularImageView.setElevation(10);
        pin.addView(circularImageView);
    }}


