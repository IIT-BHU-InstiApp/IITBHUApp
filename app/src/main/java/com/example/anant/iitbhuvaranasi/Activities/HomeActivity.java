package com.example.anant.iitbhuvaranasi.Activities;

import android.animation.Animator;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.anant.iitbhuvaranasi.Constants;
import com.example.anant.iitbhuvaranasi.Fragments.AboutFragment;
import com.example.anant.iitbhuvaranasi.Fragments.ComplainFragment;
import com.example.anant.iitbhuvaranasi.Fragments.FeedFragment;
import com.example.anant.iitbhuvaranasi.Fragments.IDCardFragment;
import com.example.anant.iitbhuvaranasi.Fragments.ImportantLinksFragment;
import com.example.anant.iitbhuvaranasi.BackendResponse.ID_card_Response;
import com.example.anant.iitbhuvaranasi.Fragments.LostAndFoundFragment;
import com.example.anant.iitbhuvaranasi.R;
import com.example.anant.iitbhuvaranasi.Interfaces.ServerCallback;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
//import com.google.firebase.messaging.FirebaseMessaging;

import static com.example.anant.iitbhuvaranasi.Activities.Feedfragment_notifcation_Activity.location2345;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    public String email;

    NavigationView navigationView;
    ProgressBar progressBar;
    FrameLayout container;
    int x = 0;
    int track = 0;


    public static String emailOfStudent = "";
    public static String name_student = "";
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



        getMenuInflater().inflate(R.menu.home_menu,menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.normal_theme:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                return true;
            case R.id.dark_theme:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {

        navigationView.getMenu().getItem(track).setChecked(true);
        super.onResume();
//        SharedPreferences sharedPrefs = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE);
//
//        int tracked = sharedPrefs.getInt("track",0);
//        navigationView.getMenu().getItem(tracked).setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences =getSharedPreferences(Constants.PREF_NAME, MODE_PRIVATE);
        String response45678 = sharedPreferences.getString(Constants.Response_Feed_Old, "2");
        email = sharedPreferences.getString(Constants.Email, Constants.Email_Key);
        createNotificationChannel();


//
        Constants.Email_Key = email;
        Log.d("no_hats2","hello4");
        ID_card_Response.method(this, new ServerCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });
        Log.d("no_hats3","hello5");
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("IIT(BHU) Varanasi");

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView =  findViewById(R.id.nav_view);
        progressBar = findViewById(R.id.progressBar);
        container = findViewById(R.id.fragment_container);


        View headerView = navigationView.getHeaderView(0);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        String personName = "", personEmail = "", personGivenName = "", personFamilyName = "";
        if (acct != null) {
            personGivenName = acct.getGivenName();
            personEmail = acct.getEmail();
            emailOfStudent = personEmail;


            //Important : Can be used later if needed
            //personName = acct.getDisplayName();
            //Stores branch of the student and year of study
            //personFamilyName = acct.getFamilyName();
        }

         TextView emailOfStudent = headerView.findViewById(R.id.email_of_student);
         TextView nameOfStudent = headerView.findViewById(R.id.name_of_student);
        SharedPreferences pref3 = getSharedPreferences(Constants.ID_Name, MODE_PRIVATE);
        Log.d("no_hats","hello");
        if(name_student.isEmpty()){
            Log.d("no_hats1","hello123");
        name_student = pref3.getString(Constants.Name_Student,personGivenName );
     }
         emailOfStudent.setText(personEmail);
         nameOfStudent.setText(name_student);
//
//                +"\npersonFamilyName="+personFamilyName);

        navigationView.setCheckedItem(R.id.nav_notifications);
        navigationView.setNavigationItemSelectedListener(this);
        if(SignInActivity.guestLoginChecker == 1){
            emailOfStudent.setText(" ");
            nameOfStudent.setText("Hello Guest User");
            if(Constants.Progress ==1) {
                ProgressDialog dialog = ProgressDialog.show(this, "", "Detecting...",
                        true);
                dialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                }, 2000);
                Constants.Progress = 2;
            }
        }


//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new FeedFragment()).commit();
//        }


        //Added by Suryansh.

        BottomNavigationView bottomNav =  findViewById(R.id.bottom_navigation);

        bottomNav.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new FeedFragment()).commit();
//        bottomNav.setSelectedItemId(R.id.feed);


        //Added by Suryansh.

//            ArrayList<String> test = ImageUrl;


//





    }
    private void itemSelectionNavigationView() {
        navigationView.getMenu().getItem(track).setChecked(true);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if(menuItem.getItemId() != R.id.nav_maps && menuItem.getItemId() != R.id.nav_academics && menuItem.getItemId() != R.id.nav_por && menuItem.getItemId() != R.id.nav_security  && menuItem.getItemId() != R.id.nav_study && menuItem.getItemId() != R.id.nav_logout ) {
            bottomNavigationView.setVisibility(View.GONE);
            crossfade(progressBar, container, false);
        }
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                switch (menuItem.getItemId()) {

                    case R.id.nav_notifications:
                        track = 0;

//                SharedPreferences.Editor editor = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor.putInt("track",0);
//                editor.commit();

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new FeedFragment()).commit();
                        bottomNavigationView.setSelectedItemId(R.id.feed);

                        bottomNavigationView.setVisibility(View.VISIBLE);



                        break;

                    case R.id.nav_maps:
//                track = 1;
//                SharedPreferences.Editor editor1 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor1.putInt("track",1);
//                editor1.commit();


                        location2345=null;
                        Intent intent1 = new Intent(HomeActivity.this, IITBHUMapActivity.class);
                        startActivity(intent1);
                        //finish();
                        x++;
                        break;
                    case R.id.nav_complain:

//                SharedPreferences.Editor editor2 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor2.putInt("track",2);
//                editor2.commit();
                        if(SignInActivity.guestLoginChecker != 1){
                            track = 2;
                            bottomNavigationView.setVisibility(View.GONE);
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new ComplainFragment()).commit();

                            x++;}
                        else{
                            itemSelectionNavigationView();
                            if(track == 0) {
                                bottomNavigationView.setVisibility(View.VISIBLE);
                            }
                            Toast toast = Toast.makeText(getApplicationContext(),"You need to LogIn for this feature",Toast.LENGTH_LONG);
                            toast.show();

                        }



                        break;
                    case R.id.lost_found:

                        if(SignInActivity.guestLoginChecker != 1){
                            track = 3;

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                    new LostAndFoundFragment()).commit();

                            x++;}
                        else{
                            itemSelectionNavigationView();
                            if(track == 0) {
                                bottomNavigationView.setVisibility(View.VISIBLE);
                            }
                            Toast toast = Toast.makeText(getApplicationContext(),"You need to LogIn for this feature",Toast.LENGTH_LONG);
                            toast.show();

                        }
                        break;


                    case R.id.important_links:
                        track = 5;
//                SharedPreferences.Editor editor5 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//                editor5.putInt("track",5);
//                editor5.commit();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ImportantLinksFragment()).commit();
                        bottomNavigationView.setVisibility(View.GONE);


                        x++;
                        break;
                    case R.id.nav_security:
                        Intent intent = new Intent(HomeActivity.this, ContactsActivity.class);
                        intent.putExtra("Intent", "security");
                        startActivity(intent);
                        finish();

                        x++;

                        break;
                    case R.id.nav_academics:
                        Intent intent4 = new Intent(HomeActivity.this, ContactsActivity.class);
                        intent4.putExtra("Intent", "academics");
                        startActivity(intent4);
                        finish();
                        x++;

                        break;
                    case R.id.nav_por:
                        Intent intent3 = new Intent(HomeActivity.this, ContactsActivity.class);
                        intent3.putExtra("Intent", "por");
                        startActivity(intent3);
                        finish();
                        x++;

                        break;
                    case R.id.nav_study:


                        String url = "https://drive.google.com/drive/u/1/folders/1UxuN1fej_4L-l9S_efyWq39h1YAzH8TW";


                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.intent.setPackage("com.android.chrome");
                        customTabsIntent.launchUrl(HomeActivity.this, Uri.parse(url));



                        break;
                    case R.id.nav_about:
                        track = 9;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new AboutFragment()).commit();
                        bottomNavigationView.setVisibility(View.GONE);
                        x++;
                        break;
                    case R.id.feedback:
                        String url1 = "https://docs.google.com/forms/d/e/1FAIpQLScGQbDEt_6gN5u3P6UsEEAEEmHE-8vvbjNUl6XhZPgBgKE0KA/viewform?usp=sf_link";
                        CustomTabsIntent.Builder builder1 = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent1 = builder1.build();
                        //customTabsIntent1.intent.setPackage("com.android.chrome");
                        customTabsIntent1.launchUrl(HomeActivity.this, Uri.parse(url1));
                        //customTabsIntent1.launchUrl(Objects.requireNonNull(this, Uri.parse(url1));



                        break;
                    case R.id.nav_logout:
                        SignInActivity.guestLoginChecker = 0;
                        mGoogleSignInClient.signOut();
//                                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        // ...
//                                    }
//                                });

                        SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor spreferencesEditor = spreferences.edit();
                        spreferencesEditor.clear();
                        spreferencesEditor.apply();

                        Intent intent2 = new Intent(HomeActivity.this, SignInActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
//                    case R.id.nav_events:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                                new ExpandedFeedFragment()).commit();
//                        bottomNavigationView.setVisibility(View.GONE);
//
//
//                        x++;//                        break;

                




            case R.id.post_feed:
                Intent postIntent = new Intent(HomeActivity.this, PostActivity.class);
                startActivity(postIntent);
                x++;
                break;
            

      





                }
                if(menuItem.getItemId() != R.id.nav_maps && menuItem.getItemId() != R.id.nav_academics&& menuItem.getItemId() != R.id.nav_por && menuItem.getItemId() != R.id.nav_security && menuItem.getItemId() != R.id.nav_study && menuItem.getItemId() != R.id.nav_logout ) {
                    crossfade(container, progressBar, false);
                }


                // Remove this listener so close by, for example, swiping do not call it again
                drawer.removeDrawerListener(this);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });



        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (x > 0) {
            navigationView.setCheckedItem(R.id.nav_notifications);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FeedFragment()).commit();
            bottomNavigationView.setSelectedItemId(R.id.feed);

            bottomNavigationView.setVisibility(View.VISIBLE);
            x = 0;
        } else if (bottomNavigationView.getSelectedItemId() != R.id.feed) {
            bottomNavigationView.setSelectedItemId(R.id.feed);

        } else {
//            SharedPreferences.Editor editor1 = getSharedPreferences("com.example.anant.iitbhuvaranasi", MODE_PRIVATE).edit();
//            editor1.putInt("track",0);
//            editor1.commit();
            super.onBackPressed();
        }


    }


    private BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;



            switch (item.getItemId()) {
                case R.id.id_card:
                    if(SignInActivity.guestLoginChecker == 1){

                        selectedFragment = new FeedFragment();
                        Toast.makeText(getApplicationContext(),"You need to Login for this feature",Toast.LENGTH_LONG).show();
                    }
                    else {
                        selectedFragment = new IDCardFragment();
                    }

                    break;

                case R.id.feed:

                        selectedFragment = new FeedFragment();

                    break;


            }


            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, "frag").commit();


            return true;

        }
    };

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        recreate();
////        Fragment fragment = getSupportFragmentManager().findFragmentByTag("frag");
////
////        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////        ft.replace(R.id.container, new FeedFragment());;
////        ft.commitAllowingStateLoss();
//
//    }

//
private void createNotificationChannel(){
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        String name = "FirebaseNotification";
        String description = "No description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("firebase", name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }


}

    private void crossfade(View viewIn, View viewOut, Boolean animateViewOut) {

        long crossfadeDuration = 200L;

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
//        viewIn.alpha = 0f;
        viewIn.setAlpha(0f);
//        viewIn.visibility = View.VISIBLE;
        viewIn.setVisibility(View.VISIBLE);
        viewIn.bringToFront();


        // Animate the in view to 100% opacity, and clear any animation
        // listener set on the view.
        viewIn.animate()
                .alpha(1f)
                .setDuration(crossfadeDuration)
                .setListener(null);

        // Animate the out view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        viewOut.animate()
                .alpha(0f)
                .setDuration( (animateViewOut) ? crossfadeDuration : 0)
            .setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    viewOut.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

    }
}

