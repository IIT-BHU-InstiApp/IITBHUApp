//package com.example.anant.iitbhuvaranasi;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.core.view.GravityCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager.widget.ViewPager;
//
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.HorizontalScrollView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupMenu;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//import android.widget.ViewFlipper;
//
//import com.google.android.material.badge.BadgeDrawable;
//import com.google.android.material.badge.BadgeUtils;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.mancj.materialsearchbar.MaterialSearchBar;
//import com.miguelcatalan.materialsearchview.MaterialSearchView;
//import com.mikhaellopez.circularimageview.CircularImageView;
//import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
//
//import java.util.ArrayList;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class Main2Activity extends AppCompatActivity {
//    private DrawerLayout drawer;
//
////    ViewFlipper flipper;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//        //images for flipper
//      int images[] = {R.drawable.sntc_astro, R.drawable.sntc_cops,R.drawable.sntc_aero, R.drawable.sntc_robotics, R.drawable.sntc_business,  R.drawable.sntc_auto, R.drawable.ecell,R.drawable.sntc_sustainablity};
////
//////        LinearLayout pin = (LinearLayout) findViewById(R.id.pin);
////
////        //configuring flipper
//////        flipper = findViewById(R.id.flipper);
//////        for(int i = 0; i < images.length; i++){
//////            addViewFlip(images[i]);
//////        }
//////        for(int i = 0; i < images.length; i++){
//////            addViewHorizontal(pin,images[i]);
//////        }
////        confiureFlipper(flipper);
//
//        //ViewPager
//        // Find the view pager that will allow the user to swipe between fragments
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        DotsIndicator dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
//
//        // Create an adapter that knows which fragment should be shown on each page
//        SimpleViewAdapter adapter = new SimpleViewAdapter(this, images);
//
//        // Set the adapter onto the view pager
//        viewPager.setAdapter(adapter);
//        dotsIndicator.setViewPager(viewPager);
//
//        //RECYCLERVIEW VERTICAL
//        ArrayList<CardViewStruct2> cardViewStructs = new ArrayList<CardViewStruct2>();
//        cardViewStructs.add(new CardViewStruct2(R.drawable.slide1, "Technex Workshop", "31-Jan-2020, 06:05 Hrs"));
//        cardViewStructs.add(new CardViewStruct2(R.drawable.slide2, "COPS ML Workshop", "29-Jan-2020, 10:05 Hrs"));
//        cardViewStructs.add(new CardViewStruct2(R.drawable.slide3, "TicTacToe", "20-Jan-2020, 17:05 Hrs"));
//        cardViewStructs.add(new CardViewStruct2(R.drawable.slide4, "IntraFreshers Cricket Tournament", "11-Jan-2020, 00:05 Hrs"));
//        cardViewStructs.add(new CardViewStruct2(R.drawable.slide5, "Neural Networks", "15-Jan-2020, 12:05 Hrs"));
//
//
//        RecyclerView rcv = (RecyclerView) findViewById(R.id.rcv);
//        RecyclerViewAdap recyclerViewAdap = new RecyclerViewAdap(this, cardViewStructs);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rcv.setLayoutManager(layoutManager);
//        rcv.setAdapter(recyclerViewAdap);
//
//        //RECYCLERVIEW HORIZONTAL
//        int[] imgId = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5};
//        RecyclerView horizontalRcv = (RecyclerView) findViewById(R.id.horizontal_rcv);
//        HorizontalRecyclerAdap horizontalRecyclerAdap = new HorizontalRecyclerAdap(this, imgId);
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        horizontalRcv.setLayoutManager(layoutManager2);
//        horizontalRcv.setAdapter(horizontalRecyclerAdap);
//
////        //Added by Suryansh.
////
////        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
////
////        bottomNav.setOnNavigationItemSelectedListener(listener);
////
////
////        //Added by Suryansh.
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_feed);
//
//        setSupportActionBar(toolbar);
//
//        drawer = findViewById(R.id.drawer_layout2);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,toolbar,
//                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//    }
//    @Override
//    public void onBackPressed() {
//        if(drawer.isDrawerOpen(GravityCompat.START)){
//            drawer.closeDrawer(GravityCompat.START);
//        }
//        else {
//            super.onBackPressed();
//        }
//
//    }
//
//
////    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
////        @Override
////        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////            Fragment selectedFragment = null;
////            int profile = 0;
////            int feed = 0;
////
////            switch (item.getItemId()) {
////                case R.id.id_card:
////                    selectedFragment = new IDCardFragment();
////                    break;
//////                case R.id.complain:
//////                    selectedFragment = new ComplainFragment();
//////                    break;
////                case R.id.profile:
////                    selectedFragment = new MyProfileFragment();
////                    break;
////                case R.id.feed:
////                    feed++;
////                    break;
//////                case R.id.more:
//////
//////
//////                    PopupMenu pum = new PopupMenu(Main2Activity.this, findViewById(R.id.more));
//////                    pum.getMenuInflater().inflate(R.menu.moretab,pum.getMenu());
//////
//////                    pum.show();
//////
//////                    break;
////
////            }
////            if (selectedFragment == null && feed == 1) {
////                RelativeLayout mainFeed = (RelativeLayout) findViewById(R.id.mainfeed);
////
////                if (mainFeed.getVisibility() == View.GONE) {
////                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
////                    startActivity(intent);
////                    finish();
////                }
////                feed = 0;
////            } else if (selectedFragment != null) {
////
////
////                RelativeLayout mainFeed = (RelativeLayout) findViewById(R.id.mainfeed);
////                mainFeed.setVisibility(View.GONE);
////
////                getSupportFragmentManager().beginTransaction().replace(R.id.new_container, selectedFragment).commit();
////
////
////            }
////
////
////            return true;
////
////        }
////    };
//}
////    public void confiureFlipper(ViewFlipper flipper){
////        flipper.setFlipInterval(4000);
////        flipper.setAutoStart(true);
////        flipper.setInAnimation(this, R.anim.slideinright);
////        flipper.setOutAnimation(this, R.anim.slideoutleft);
////        flipper.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(MainActivity.this,"fuck",Toast.LENGTH_SHORT).show();
////            }
////        });
////
////    }
//
////    public void addViewFlip(int img){
////        ImageView imageView = new ImageView(this);
////        imageView.setBackgroundResource(img);
////
////        flipper.addView(imageView);
////
////    }
////    public void addViewHorizontal(LinearLayout pin,int img){
////
////        CircleImageView circularImageView =  new CircleImageView(this);
////        circularImageView.setLayoutParams(new ViewGroup.LayoutParams(250,250));
////        circularImageView.setImageResource(img);
////        circularImageView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(Main2Activity.this, "This is a HorizontalView Item",Toast.LENGTH_SHORT).show();
////            }
////        });
////        circularImageView.setBorderWidth(2);
////        circularImageView.setPadding(10,0,10,0);
////        circularImageView.setBorderColor(getResources().getColor(R.color.colorblack));
////        circularImageView.setElevation(10);
////        pin.addView(circularImageView);
////    }}
//
//
